package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Event;
import bj.csam.playlist.PlayManager.Service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/test/events")
@RequiredArgsConstructor
@Slf4j
public class TestEventController {

    private final EventService eventService;

    @GetMapping
    public String showEventsPage(Model model) {
        log.info("Affichage de la page de test des événements");
        List<Event> events = eventService.findAll();
        model.addAttribute("events", events);
        return "test/events";
    }

    @PostMapping("/create")
    public String createEvent(@RequestParam String title,
                            @RequestParam(required = false) String description,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDate,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reminderDate,
                            RedirectAttributes redirectAttributes) {
        log.info("Création d'un événement de test: {}", title);
        try {
            Event event = new Event();
            event.setTitle(title);
            event.setDescription(description);
            event.setEventDate(eventDate);
            event.setReminderDate(reminderDate);

            eventService.save(event);

            redirectAttributes.addFlashAttribute("flashMessage", "Événement créé avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la création de l'événement", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la création de l'événement");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/events";
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        log.info("Suppression de l'événement ID: {}", id);
        try {
            eventService.deleteById(id);
            redirectAttributes.addFlashAttribute("flashMessage", "Événement supprimé avec succès !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression de l'événement", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression de l'événement");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/events";
    }

    @PostMapping("/clear")
    public String clearAllEvents(RedirectAttributes redirectAttributes) {
        log.info("Suppression de tous les événements de test");
        try {
            List<Event> events = eventService.findAll();
            for (Event event : events) {
                eventService.deleteById(event.getId());
            }
            redirectAttributes.addFlashAttribute("flashMessage", "Tous les événements ont été supprimés !");
            redirectAttributes.addFlashAttribute("flashType", "success");
        } catch (Exception e) {
            log.error("Erreur lors de la suppression des événements", e);
            redirectAttributes.addFlashAttribute("flashMessage", "Erreur lors de la suppression des événements");
            redirectAttributes.addFlashAttribute("flashType", "error");
        }
        return "redirect:/test/events";
    }

    @PostMapping("/check-reminders")
    @ResponseBody
    public String checkReminders() {
        log.info("Vérification manuelle des rappels");
        try {
            int remindersSent = eventService.checkAndSendReminders();
            return remindersSent + " rappel(s) envoyé(s) avec succès !";
        } catch (Exception e) {
            log.error("Erreur lors de la vérification des rappels", e);
            return "Erreur lors de la vérification des rappels";
        }
    }
}