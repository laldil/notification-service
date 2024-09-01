package kz.edu.astanait.notification_service.mapper;

import kz.edu.astanait.notification_service.dto.receiver.ReceiverContactDto;
import kz.edu.astanait.notification_service.model.ReceiverContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

@Mapper
public interface ReceiverContactMapper {

    ReceiverContactMapper MAPPER = Mappers.getMapper(ReceiverContactMapper.class);

    ReceiverContactEntity mapToEntity(ReceiverContactDto dto);

    ReceiverContactDto mapToDto(ReceiverContactEntity entity);

    default List<ReceiverContactDto> mapToDtoList(List<ReceiverContactEntity> entityList) {
        if (entityList.isEmpty()) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::mapToDto).toList();
    }
}
