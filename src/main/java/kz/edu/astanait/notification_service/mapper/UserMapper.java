package kz.edu.astanait.notification_service.mapper;

import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.dto.user.UserDto;
import kz.edu.astanait.notification_service.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserEntity mapToEntity(CreateUserRequest request);

    UserDto mapToDto(UserEntity entity);
}
