package kz.edu.astanait.senderservice.service.impl;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import kz.edu.astanait.senderservice.enums.ContactType;
import kz.edu.astanait.senderservice.properties.TwilioProperties;
import kz.edu.astanait.senderservice.service.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Boolean> sendNotification(String contact, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var sms = Message.creator(
                        new PhoneNumber(contact),
                        new PhoneNumber(twilioProperties.getNumber()),
                        message
                ).create();
                log.info("Sent sms notification to {}", sms.getTo());
                return true;
            } catch (Exception e) {
                log.error("Failed to send SMS notification to {}: {}", contact, e.getMessage());
                return false;
            }
        });
    }
}
