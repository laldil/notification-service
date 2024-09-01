package kz.edu.astanait.notification_service.serivce.impl;

import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.dto.user.JwtResponse;
import kz.edu.astanait.notification_service.dto.user.LoginRequest;
import kz.edu.astanait.notification_service.mapper.UserMapper;
import kz.edu.astanait.notification_service.model.UserEntity;
import kz.edu.astanait.notification_service.serivce.AuthService;
import kz.edu.astanait.notification_service.serivce.UserService;
import kz.edu.astanait.notification_service.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Override
    public JwtResponse register(CreateUserRequest request) {
        var user = userService.createUser(request);
        return buildJwtResponse(user);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Email or password is not correct");
        }

        var user = userService.findByEmail(request.getEmail());
        return buildJwtResponse(user);
    }

    private JwtResponse buildJwtResponse(UserEntity user) {
        return JwtResponse.builder()
                .user(UserMapper.MAPPER.mapToDto(user))
                .accessToken(jwtUtils.generateAccessToken(user))
                .refreshToken(jwtUtils.generateRefreshToken(user))
                .build();
    }
}
