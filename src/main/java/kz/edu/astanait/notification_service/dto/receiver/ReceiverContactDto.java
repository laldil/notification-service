package kz.edu.astanait.notification_service.dto.receiver;

import kz.edu.astanait.notification_service.model.enums.ContactType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiverContactDto {
    private ContactType contactType;
    private String contact;
}
