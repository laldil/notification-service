package kz.edu.astanait.senderservice.service.impl;

import kz.edu.astanait.senderservice.dto.NotificationDto;
import kz.edu.astanait.senderservice.service.NotificationService;
import kz.edu.astanait.senderservice.service.factory.NotificationFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationFactory;

    @Override
    @KafkaListener(topics = "notification-topic", groupId = "notification",
            containerFactory = "notificationKafkaListenerContainerFactory")
    public void sendNotification(NotificationDto request) {
        log.info("Received message from kafka. Notification for contact: {}", request.getContact());
        try {
            var sender = notificationFactory.getSender(request.getContactType());

            CompletableFuture<Boolean> future = sender.sendNotification(request.getContact(), request.getMessage());
            future.thenAccept(isSent -> {
                if (isSent) {
                    log.info("Notification successfully sent to: {}", request.getContact());
                } else {
                    throw new RuntimeException("Notification sending failed");
                }
            }).exceptionally(e -> {
                log.error("Error while sending notification to {}: {}", request.getContact(), e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            });
        } catch (Exception e) {
            log.error("Error while sending notification to {}: {}", request.getContact(), e.getMessage());
            throw e;
        }
    }
}
