package bj.csam.playlist.PlayManager.Repository;

import bj.csam.playlist.PlayManager.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}