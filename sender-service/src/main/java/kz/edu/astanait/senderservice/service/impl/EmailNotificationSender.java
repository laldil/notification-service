package kz.edu.astanait.senderservice.service.impl;


import jakarta.mail.MessagingException;
import kz.edu.astanait.senderservice.enums.ContactType;
import kz.edu.astanait.senderservice.service.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Boolean> sendNotification(String contact, String message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var mimeMessage = emailSender.createMimeMessage();

                var helper = new MimeMessageHelper(mimeMessage, "UTF-8");
                helper.setFrom(email);
                helper.setTo(contact);
                helper.setSubject("Notification from service");
                helper.setText(message);

                emailSender.send(mimeMessage);
                log.info("Sent email notification to {}", contact);
                return true;
            } catch (MessagingException e) {
                log.error("Failed to send email notification to {}: {}", contact, e.getMessage());
                return false;
            }
        });
    }
}
