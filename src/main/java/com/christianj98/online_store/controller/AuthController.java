package com.christianj98.online_store.controller;

import com.christianj98.online_store.dto.ForgotPasswordRequest;
import com.christianj98.online_store.dto.LoginRequestDto;
import com.christianj98.online_store.dto.LoginResponseDto;
import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        userService.createUser(registerRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return userService.authenticate(loginRequestDto);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> processForgotPassword(@RequestBody ForgotPasswordRequest request, HttpServletRequest httpServletRequest) {
        String appUrl = httpServletRequest.getScheme() + "://" +
                httpServletRequest.getServerName() + ":" +
                httpServletRequest.getServerPort();

        userService.createPasswordResetToken(request.getEmail(), appUrl);
        return ResponseEntity.ok("Jeśli adres email istnieje, wysłaliśmy wiadomość resetującą hasło.");
    }

    @GetMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword()
}
