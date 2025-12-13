package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Image;
import bj.csam.playlist.PlayManager.Service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/test/images")
@RequiredArgsConstructor
@Slf4j
public class TestImageController {

    private final ImageService imageService;

    @GetMapping
    public String showImagesPage(Model model) {
        log.info("Affichage de la page de test des images");
        List<Image> images = imageService.findAll();
        model.addAttribute("images", images);
        return "test/images";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) String description,
                            RedirectAttributes redirectAttributes) {
        log.info("Upload d'une image de test: {}", file.getOriginalFilename());
        try {
            Image image = imageService.save(file, title, description);
            redirectAttributes.addFlashAttribute("flashMessage", "Image uploadée avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de l'upload de l'image", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de l'upload: " + e.getMessage());
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/images";
    }

    @PostMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Suppression de l'image ID: {}", id);
        try {
            imageService.deleteById(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Image supprimée avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'image", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression de l'image");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/images";
    }

    @PostMapping("/clear")
    public String clearAllImages(RedirectAttributes redirectAttributes) {
        log.info("Suppression de toutes les images de test");
        try {
            List<Image> images = imageService.findAll();
            for (Image image : images) {
                imageService.deleteById(image.getId());
            }
            redirectAttributes.addFlashAttribute("flashMessage", "Toutes les images ont été supprimées !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression des images", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression des images");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/images";
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/images").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de l'image: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }
}