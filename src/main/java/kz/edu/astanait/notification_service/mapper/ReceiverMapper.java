package kz.edu.astanait.notification_service.mapper;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverDto;
import kz.edu.astanait.notification_service.model.ReceiverEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReceiverMapper {

    ReceiverMapper MAPPER = Mappers.getMapper(ReceiverMapper.class);

    @Mapping(target = "contacts", expression = "java(ReceiverContactMapper.MAPPER.mapToDtoList(entity.getContacts()))")
    ReceiverDto mapToDto(ReceiverEntity entity);

    @Mapping(target = "contacts", ignore = true)
    ReceiverEntity mapToEntity(ReceiverDto dto);
}
