package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Notification;
import bj.csam.playlist.PlayManager.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/test/notifications")
@RequiredArgsConstructor
@Slf4j
public class TestNotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public String showNotificationsPage(Model model) {
        log.info("Affichage de la page de test des notifications");
        List<Notification> notifications = notificationService.findAll();
        model.addAttribute("notifications", notifications);
        return "test/notifications";
    }

    @PostMapping("/create")
    public String createNotification(@RequestParam String message,
                                   @RequestParam String type,
                                   RedirectAttributes redirectAttributes) {
        log.info("Création d'une notification de test: {} - {}", type, message);
        try {
            Notification notification = new Notification();
            notification.setMessage(message);
            notification.setType(type);

            notificationService.save(notification);

            redirectAttributes.addFlashAttribute("flashMessage", "Notification créée avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la création de la notification", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la création de la notification");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/notifications";
    }

    @PostMapping("/delete/{id}")
    public String deleteNotification(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Suppression de la notification ID: {}", id);
        try {
            notificationService.deleteById(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Notification supprimée avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de la notification", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression de la notification");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/notifications";
    }

    @PostMapping("/clear")
    public String clearAllNotifications(RedirectAttributes redirectAttributes) {
        log.info("Suppression de toutes les notifications de test");
        try {
            List<Notification> notifications = notificationService.findAll();
            for (Notification notification : notifications) {
                notificationService.deleteById(notification.getId());
            }
            redirectAttributes.addFlashAttribute("flashMessage", "Toutes les notifications ont été supprimées !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression des notifications", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression des notifications");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/notifications";
    }
}