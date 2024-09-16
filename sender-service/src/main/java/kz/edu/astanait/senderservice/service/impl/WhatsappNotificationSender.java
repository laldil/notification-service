package kz.edu.astanait.senderservice.service.impl;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import kz.edu.astanait.senderservice.enums.ContactType;
import kz.edu.astanait.senderservice.properties.TwilioProperties;
import kz.edu.astanait.senderservice.service.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WhatsappNotificationSender implements NotificationSender {

    private final static String WHATSAPP_PREFIX = "whatsapp:";

    private final TwilioProperties twilioProperties;

    @Override
    public ContactType getType() {
        return ContactType.WHATSAPP;
    }

    @Override
    public void sendNotification(String contact, String message) {
        var notification = Message.creator(
                new PhoneNumber(WHATSAPP_PREFIX.concat(contact)),
                new PhoneNumber(WHATSAPP_PREFIX.concat(twilioProperties.getWhatsappNumber())),
                message
        ).create();
        log.info("Sent whatsapp notification to {}", notification.getTo());
    }
}
