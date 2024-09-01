package kz.edu.astanait.notification_service.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {
    private final String tokenType = "Bearer ";
    private UserDto user;
    private String accessToken;
    private String refreshToken;
}
