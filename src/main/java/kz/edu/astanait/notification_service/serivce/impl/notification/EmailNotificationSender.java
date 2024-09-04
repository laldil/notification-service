package kz.edu.astanait.notification_service.serivce.impl.notification;

import jakarta.mail.MessagingException;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.serivce.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailNotificationSender implements NotificationSender {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public ContactType getType() {
        return ContactType.EMAIL;
    }

    @Async
    @Override
    public void sendNotification(ReceiverContactEntity contact, String message) {
        try {
            var mimeMessage = emailSender.createMimeMessage();

            var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(email);
            helper.setTo(contact.getContact());
            helper.setSubject("Notification from service");
            helper.setText(message);

            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }
    }
}
