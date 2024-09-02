package kz.edu.astanait.notification_service.dto.receiver;

import jakarta.validation.constraints.NotNull;
import kz.edu.astanait.notification_service.model.enums.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiverContactDto {
    @NotNull(message = "Enter the contact")
    private String contact;

    @NotNull(message = "Enter the contact type")
    private ContactType contactType;
}
