package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.notification.SendNotificationRequest;
import kz.edu.astanait.notification_service.serivce.MessageTemplateService;
import kz.edu.astanait.notification_service.serivce.NotificationService;
import kz.edu.astanait.notification_service.serivce.ReceiverContactService;
import kz.edu.astanait.notification_service.serivce.factory.NotificationFactory;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationFactory notificationFactory;

    private final ReceiverContactService receiverContactService;
    private final MessageTemplateService templateService;

    @Override
    public void sendNotification(SendNotificationRequest request) {
        var contacts = receiverContactService.getReceiverContacts(SecurityUtils.getCurrentId(), request.getContactType());
        var template = templateService.getMessageTemplate(request.getTemplateId());

        for (var contact : contacts) {
            var sender = notificationFactory.getSender(contact.getContactType());
            sender.sendNotification(contact, template.getTemplate());
        }
    }
}
