package kz.edu.astanait.notification_service.dto.notification;

import kz.edu.astanait.notification_service.model.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private String contact;
    private String message;
    private ContactType contactType;
}
