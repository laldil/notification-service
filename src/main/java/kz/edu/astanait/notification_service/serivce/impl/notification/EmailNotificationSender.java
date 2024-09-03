package kz.edu.astanait.notification_service.serivce.impl.notification;

import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.serivce.NotificationSender;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender emailSender;

    @Override
    public ContactType getType() {
        return ContactType.EMAIL;
    }

    @Async
    @Override
    public void sendNotification(ReceiverContactEntity contact, String message) {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("Notification service");
        mailMessage.setTo(contact.getContact());
        mailMessage.setSubject("Subject");
        mailMessage.setText(message);

        emailSender.send(mailMessage);
    }
}
