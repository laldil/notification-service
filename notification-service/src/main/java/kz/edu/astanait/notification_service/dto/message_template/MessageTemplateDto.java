package kz.edu.astanait.notification_service.dto.message_template;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageTemplateDto {
    private Long id;
    private String title;
    private String template;
}
