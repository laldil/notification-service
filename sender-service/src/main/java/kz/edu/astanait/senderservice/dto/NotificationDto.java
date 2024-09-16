package kz.edu.astanait.senderservice.dto;

import kz.edu.astanait.senderservice.enums.ContactType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationDto {
    private String contact;
    private String message;
    private ContactType contactType;
}
