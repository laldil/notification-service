package kz.edu.astanait.notification_service.dto.user;

import kz.edu.astanait.notification_service.model.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Role role;
}
