package kz.edu.astanait.notification_service.dto.receiver;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiverDto {
    private String name;
    private List<ReceiverContactDto> contacts;
}
