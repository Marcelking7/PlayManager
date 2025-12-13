package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByReminderDateBeforeAndReminderSentFalse(LocalDateTime now);
}