package kz.edu.astanait.notification_service.mapper;

import kz.edu.astanait.notification_service.dto.message_template.CreateMessageTemplateRequest;
import kz.edu.astanait.notification_service.dto.message_template.MessageTemplateDto;
import kz.edu.astanait.notification_service.model.MessageTemplateEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageTemplateMapper {

    MessageTemplateMapper MAPPER = Mappers.getMapper(MessageTemplateMapper.class);

    MessageTemplateEntity mapToEntity(CreateMessageTemplateRequest request);

    MessageTemplateDto mapToDto(MessageTemplateEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(MessageTemplateDto dto, @MappingTarget MessageTemplateEntity entity);
}
