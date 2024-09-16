package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.notification.NotificationDto;
import kz.edu.astanait.notification_service.dto.notification.SendNotificationRequest;
import kz.edu.astanait.notification_service.serivce.MessageTemplateService;
import kz.edu.astanait.notification_service.serivce.NotificationService;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    private final ReceiverContactService receiverContactService;
    private final MessageTemplateService templateService;

    @Override
    public void sendNotification(SendNotificationRequest request) {
        var contacts = receiverContactService.getReceiverContacts(SecurityUtils.getCurrentId(), request.getContactType());
        var template = templateService.getMessageTemplate(request.getTemplateId());

        contacts.parallelStream().forEach(contact -> {
            var notification = new NotificationDto(contact.getContact(), template.getTemplate(), contact.getContactType());
            kafkaTemplate.send("notification-topic", notification).whenComplete((result, e) -> {
                if (Objects.isNull(e)) {
                    log.info("Sent notification to sender-service: {} | offset - {}", contact.getContact(), result.getRecordMetadata().offset());
                } else {
                    log.error("Failed to send notification to sender-service: {}. Message: {}", contact.getContact(), e.getMessage());
                }
            });
        });
    }
}
