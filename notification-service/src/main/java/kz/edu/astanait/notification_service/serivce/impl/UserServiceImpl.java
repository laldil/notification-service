package kz.edu.astanait.notification_service.serivce.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.mapper.UserMapper;
import kz.edu.astanait.notification_service.model.UserEntity;
import kz.edu.astanait.notification_service.model.enums.Role;
import kz.edu.astanait.notification_service.repository.UserRepository;
import kz.edu.astanait.notification_service.serivce.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        var user = UserMapper.MAPPER.mapToEntity(request);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id %d not found".formatted(id)));
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email %s not found".formatted(email)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = findByEmail(email);
        return new User(
                user.getName(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
