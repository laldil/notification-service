package kz.edu.astanait.notification_service.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Email is not valid")
    @NotNull(message = "Enter the email")
    private String email;

    @NotNull(message = "Enter the password")
    private String password;
}
