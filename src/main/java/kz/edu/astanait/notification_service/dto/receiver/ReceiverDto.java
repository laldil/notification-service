package kz.edu.astanait.notification_service.dto.receiver;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReceiverDto {
    @NotNull(message = "Enter the name")
    private String name;

    private List<ReceiverContactDto> contacts = new ArrayList<>();
}
