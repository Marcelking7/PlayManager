package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Sound;
import bj.csam.playlist.PlayManager.Service.SoundService;
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
@RequestMapping("/test/sounds")
@RequiredArgsConstructor
@Slf4j
public class TestSoundController {

    private final SoundService soundService;

    @GetMapping
    public String showSoundsPage(Model model) {
        log.info("Affichage de la page de test des sons");
        List<Sound> sounds = soundService.findAll();
        model.addAttribute("sounds", sounds);
        return "test/sounds";
    }

    @PostMapping("/upload")
    public String uploadSound(@RequestParam("file") MultipartFile file,
                            @RequestParam(required = false) String title,
                            @RequestParam(required = false) String description,
                            @RequestParam String eventType,
                            RedirectAttributes redirectAttributes) {
        log.info("Upload d'un son de test: {}", file.getOriginalFilename());
        try {
            Sound sound = soundService.save(file, title, description, eventType);
            redirectAttributes.addFlashAttribute("flashMessage", "Son uploadé avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de l'upload du son", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de l'upload: " + e.getMessage());
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/sounds";
    }

    @PostMapping("/delete/{id}")
    public String deleteSound(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Suppression du son ID: {}", id);
        try {
            soundService.deleteById(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Son supprimé avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression du son", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression du son");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/sounds";
    }

    @PostMapping("/clear")
    public String clearAllSounds(RedirectAttributes redirectAttributes) {
        log.info("Suppression de tous les sons de test");
        try {
            List<Sound> sounds = soundService.findAll();
            for (Sound sound : sounds) {
                soundService.deleteById(sound.getId());
            }
            redirectAttributes.addFlashAttribute("flashMessage", "Tous les sons ont été supprimés !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression des sons", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression des sons");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/sounds";
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveSound(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/sounds").resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Determine content type based on file extension
                String contentType = "audio/mpeg"; // default
                if (filename.toLowerCase().endsWith(".wav")) {
                    contentType = "audio/wav";
                } else if (filename.toLowerCase().endsWith(".ogg")) {
                    contentType = "audio/ogg";
                } else if (filename.toLowerCase().endsWith(".aac")) {
                    contentType = "audio/aac";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du son: {}", filename, e);
            return ResponseEntity.notFound().build();
        }
    }
}