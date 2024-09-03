package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;

public interface NotificationSender {

    ContactType getType();

    void sendNotification(ReceiverContactEntity contact, String message);
}
