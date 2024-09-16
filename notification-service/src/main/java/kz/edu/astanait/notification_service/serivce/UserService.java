package kz.edu.astanait.notification_service.serivce;

import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity createUser(CreateUserRequest request);

    UserEntity findById(Long id);

    UserEntity findByEmail(String email);
}
