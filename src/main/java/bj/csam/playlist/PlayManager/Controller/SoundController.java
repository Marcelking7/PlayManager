package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Service.SoundService;
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
@RequestMapping("/sounds")
@RequiredArgsConstructor
public class SoundController {

    private final SoundService soundService;

    @GetMapping
    public String listSounds(Model model) {
        model.addAttribute("sounds", soundService.findAll());
        return "sounds";
    }

    @PostMapping("/upload")
    public String uploadSound(@RequestParam("file") MultipartFile file, @RequestParam("eventType") String eventType, RedirectAttributes ra) {
        try {
            soundService.save(file, eventType);
            ra.addFlashAttribute("flashMessage", "Son uploadé avec succès");
            ra.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            ra.addFlashAttribute("flashMessage", "Erreur lors de l'upload");
            ra.addFlashAttribute("flashType", "error");
        }
        return "redirect:/sounds";
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getSound(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("uploads/sounds").resolve(filename);
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
    public String deleteSound(@PathVariable Long id, RedirectAttributes ra) {
        soundService.deleteById(id);
        ra.addFlashAttribute("flashMessage", "Son supprimé avec succès");
        ra.addFlashAttribute("flashType", "success");
        return "redirect:/sounds";
    }
}