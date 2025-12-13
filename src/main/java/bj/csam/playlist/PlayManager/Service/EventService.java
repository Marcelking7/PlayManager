package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Model.Event;
import bj.csam.playlist.PlayManager.Repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> findPendingReminders() {
        return eventRepository.findByReminderDateBeforeAndReminderSentFalse(LocalDateTime.now());
    }

    public void markReminderSent(Event event) {
        event.setReminderSent(true);
        eventRepository.save(event);
    }

    public int checkAndSendReminders() {
        List<Event> pendingReminders = findPendingReminders();
        int sentCount = 0;

        for (Event event : pendingReminders) {
            // Simuler l'envoi du rappel (dans un vrai système, cela enverrait un email/notification)
            System.out.println("Rappel envoyé pour l'événement: " + event.getTitle() +
                    " prévu le " + event.getEventDate());

            // Marquer le rappel comme envoyé
            markReminderSent(event);
            sentCount++;
        }

        return sentCount;
    }
}