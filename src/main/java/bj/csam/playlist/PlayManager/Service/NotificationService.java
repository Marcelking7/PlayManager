package bj.csam.playlist.PlayManager.Service;

import bj.csam.playlist.PlayManager.Model.Notification;
import bj.csam.playlist.PlayManager.Repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public void deleteById(Long id) {
        notificationRepository.deleteById(id);
    }
}