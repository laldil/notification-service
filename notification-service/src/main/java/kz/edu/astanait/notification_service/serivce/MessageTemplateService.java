package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.message_template.CreateMessageTemplateRequest;
import kz.edu.astanait.notification_service.dto.message_template.MessageTemplateDto;
import kz.edu.astanait.notification_service.model.MessageTemplateEntity;

import java.util.List;

public interface MessageTemplateService {

    MessageTemplateDto createMessageTemplate(CreateMessageTemplateRequest request);

    List<MessageTemplateDto> getMessageTemplates();

    MessageTemplateDto updateMessageTemplate(MessageTemplateDto dto);

    Boolean deleteMessageTemplate(Long id);

    MessageTemplateEntity getMessageTemplate(Long id);
}
