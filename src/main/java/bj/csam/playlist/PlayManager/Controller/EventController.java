package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Event;
import bj.csam.playlist.PlayManager.Service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public String listEvents(Model model) {
        model.addAttribute("events", eventService.findAll());
        return "events";
    }

    @PostMapping("/add")
    public String addEvent(@ModelAttribute Event event, RedirectAttributes ra) {
        eventService.save(event);
        ra.addFlashAttribute("flashMessage", "Événement ajouté avec succès");
        ra.addFlashAttribute("flashType", "success");
        return "redirect:/events";
    }

    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id, RedirectAttributes ra) {
        eventService.deleteById(id);
        ra.addFlashAttribute("flashMessage", "Événement supprimé avec succès");
        ra.addFlashAttribute("flashType", "success");
        return "redirect:/events";
    }
}