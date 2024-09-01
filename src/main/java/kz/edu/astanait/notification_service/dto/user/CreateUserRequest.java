package kz.edu.astanait.notification_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotNull(message = "Enter your name")
    private String name;

    @NotNull(message = "Enter your email")
    @Email(message = "Email is not correct")
    private String email;

    @NotNull(message = "Enter your password")
    private String password;

    private String phoneNumber;
}
