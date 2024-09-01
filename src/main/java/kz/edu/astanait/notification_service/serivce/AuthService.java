package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.dto.user.JwtResponse;
import kz.edu.astanait.notification_service.dto.user.LoginRequest;

public interface AuthService {
    JwtResponse register(CreateUserRequest request);

    JwtResponse login(LoginRequest request);
}
