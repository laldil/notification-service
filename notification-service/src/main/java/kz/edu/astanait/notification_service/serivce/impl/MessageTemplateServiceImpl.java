package kz.edu.astanait.notification_service.serivce.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.notification_service.dto.message_template.CreateMessageTemplateRequest;
import kz.edu.astanait.notification_service.dto.message_template.MessageTemplateDto;
import kz.edu.astanait.notification_service.exception.AccessForbiddenException;
import kz.edu.astanait.notification_service.mapper.MessageTemplateMapper;
import kz.edu.astanait.notification_service.model.MessageTemplateEntity;
import kz.edu.astanait.notification_service.repository.MessageTemplateRepository;
import kz.edu.astanait.notification_service.serivce.MessageTemplateService;
import kz.edu.astanait.notification_service.serivce.UserService;
import kz.edu.astanait.notification_service.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageTemplateServiceImpl implements MessageTemplateService {

    private final MessageTemplateRepository repository;

    private final UserService userService;

    @Override
    public MessageTemplateDto createMessageTemplate(CreateMessageTemplateRequest request) {
        var owner = userService.findById(SecurityUtils.getCurrentId());

        var template = MessageTemplateMapper.MAPPER.mapToEntity(request);
        template.setOwner(owner);

        var savedTemplate = repository.save(template);
        return MessageTemplateMapper.MAPPER.mapToDto(savedTemplate);
    }

    @Override
    public List<MessageTemplateDto> getMessageTemplates() {
        var templates = repository.findByOwnerId(SecurityUtils.getCurrentId());
        return templates.stream().map(MessageTemplateMapper.MAPPER::mapToDto).toList();
    }

    @Override
    public MessageTemplateDto updateMessageTemplate(MessageTemplateDto dto) {
        var template = getMessageTemplate(dto.getId());

        validateAccess(template);
        MessageTemplateMapper.MAPPER.updateEntity(dto, template);

        return MessageTemplateMapper.MAPPER.mapToDto(repository.save(template));
    }

    @Override
    public Boolean deleteMessageTemplate(Long id) {
        var template = getMessageTemplate(id);

        validateAccess(template);

        repository.delete(template);
        return true;
    }

    @Override
    public MessageTemplateEntity getMessageTemplate(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Template with id %d not found".formatted(id)));
    }

    private void validateAccess(MessageTemplateEntity template) {
        if (!template.getOwner().getId().equals(SecurityUtils.getCurrentId())) {
            throw new AccessForbiddenException("You don't own this template");
        }
    }
}
