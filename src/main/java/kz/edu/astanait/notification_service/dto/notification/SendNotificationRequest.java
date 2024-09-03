package kz.edu.astanait.notification_service.dto.notification;

import jakarta.validation.constraints.NotNull;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendNotificationRequest {
    @NotNull(message = "Select contact type")
    private ContactType contactType;

    @NotNull(message = "Enter the message")
    private String message;
}
