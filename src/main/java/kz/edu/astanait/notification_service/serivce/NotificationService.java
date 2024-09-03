package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.notification.SendNotificationRequest;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;

import java.util.List;

public interface NotificationService {

    void sendNotification(SendNotificationRequest request);
}
