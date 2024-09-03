package kz.edu.astanait.notification_service.serivce.impl.notification;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import kz.edu.astanait.notification_service.config.properties.TwilioProperties;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.serivce.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SmsNotificationSender implements NotificationSender {

    private final TwilioProperties twilioProperties;

    @Override
    public ContactType getType() {
        return ContactType.SMS;
    }

    @Override
    public void sendNotification(ReceiverContactEntity contact, String message) {
        var sms = Message.creator(
                new PhoneNumber(contact.getContact()),
                new PhoneNumber(twilioProperties.getNumber()),
                message
        ).create();
        log.info("Sent message to {}", sms.getTo());
    }
}
