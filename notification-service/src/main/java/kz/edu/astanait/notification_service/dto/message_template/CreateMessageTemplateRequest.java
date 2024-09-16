package kz.edu.astanait.notification_service.dto.message_template;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageTemplateRequest {
    @NotNull(message = "Enter the title")
    private String title;

    @NotNull(message = "Enter the template")
    private String template;
}
