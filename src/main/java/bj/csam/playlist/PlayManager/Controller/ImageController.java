package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public String listImages(Model model) {
        model.addAttribute("images", imageService.findAll());
        return "images";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        try {
            imageService.save(file);
            ra.addFlashAttribute("flashMessage", "Image uploadée avec succès");
            ra.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("flashMessage", "Erreur lors de l'upload");
            ra.addFlashAttribute("flashType", "error");
        }
        return "redirect:/images";
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/images").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteImage(@PathVariable Long id, RedirectAttributes ra) {
        imageService.deleteById(id);
        ra.addFlashAttribute("flashMessage", "Image supprimée avec succès");
        ra.addFlashAttribute("flashType", "success");
        return "redirect:/images";
    }
}