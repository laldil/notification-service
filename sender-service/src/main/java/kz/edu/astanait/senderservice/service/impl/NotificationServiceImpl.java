package kz.edu.astanait.senderservice.service.impl;

import kz.edu.astanait.senderservice.dto.NotificationDto;
import kz.edu.astanait.senderservice.service.NotificationService;
import kz.edu.astanait.senderservice.service.factory.NotificationFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationFactory;

    @Override
    @Async
    @KafkaListener(topics = "notification-topic", groupId = "notification",
            containerFactory = "notificationKafkaListenerContainerFactory")
    public void sendNotification(NotificationDto request) {
        log.info("Received notification: {}", request.getContact());
        var sender = notificationFactory.getSender(request.getContactType());
        sender.sendNotification(request.getContact(), request.getMessage());
    }
}
