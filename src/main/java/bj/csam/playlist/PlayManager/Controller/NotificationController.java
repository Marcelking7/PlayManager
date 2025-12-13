package bj.csam.playlist.PlayManager.Controller;

import bj.csam.playlist.PlayManager.Model.Notification;
import bj.csam.playlist.PlayManager.Service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @MessageMapping("/notify")
    @SendTo("/topic/notifications")
    public Notification sendNotification(Notification notification) {
        notificationService.save(notification);
        return notification;
    }
}