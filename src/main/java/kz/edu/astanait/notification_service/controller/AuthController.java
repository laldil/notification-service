package kz.edu.astanait.notification_service.controller;

import jakarta.validation.Valid;
import kz.edu.astanait.notification_service.dto.user.CreateUserRequest;
import kz.edu.astanait.notification_service.dto.user.JwtResponse;
import kz.edu.astanait.notification_service.dto.user.LoginRequest;
import kz.edu.astanait.notification_service.serivce.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(authService.register(request));
    }
}
