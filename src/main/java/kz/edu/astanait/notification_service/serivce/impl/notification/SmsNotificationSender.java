package kz.edu.astanait.notification_service.serivce.impl.notification;

import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.serivce.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsNotificationSender implements NotificationSender {
    @Override
    public ContactType getType() {
        return ContactType.SMS;
    }

    @Override
    public void sendNotification(ReceiverContactEntity contact, String message) {
        log.info("""
                Contacts: %s
                Message: %s
                """.formatted(contact.getContact(), message));

    }
}
