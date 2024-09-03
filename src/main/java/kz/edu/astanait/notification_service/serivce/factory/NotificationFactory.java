package kz.edu.astanait.notification_service.serivce.factory;

import kz.edu.astanait.notification_service.model.enums.ContactType;
import kz.edu.astanait.notification_service.serivce.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationFactory {
    private final Map<ContactType, NotificationSender> senderMap = new HashMap<>();

    @Autowired
    public NotificationFactory(List<NotificationSender> senders) {
        senders.forEach(sender -> senderMap.put(sender.getType(), sender));
    }

    public NotificationSender getSender(ContactType type) {
        var sender = senderMap.get(type);
        if (sender == null) {
            throw new IllegalArgumentException("Unsupported contact type: " + type);
        }
        return sender;
    }
}
