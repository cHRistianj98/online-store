package com.christianj98.online_store.controller;

import com.christianj98.online_store.dto.ForgotPasswordRequest;
import com.christianj98.online_store.dto.LoginRequestDto;
import com.christianj98.online_store.dto.LoginResponseDto;
import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.dto.ResetPasswordRequest;
import com.christianj98.online_store.dto.exception.ErrorDetails;
import com.christianj98.online_store.exception.ApiException;
import com.christianj98.online_store.service.PasswordResetService;
import com.christianj98.online_store.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordResetService passwordResetService;

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

        passwordResetService.createPasswordResetTokenForUser(request.getEmail(), appUrl);
        return ResponseEntity.ok("Jeśli adres email istnieje, wysłaliśmy wiadomość resetującą hasło.");
    }

    @GetMapping("/reset-password/{token}")
    public ResponseEntity<?> processResetPassword(@RequestBody ResetPasswordRequest request) {
        final var token = request.getToken();
        passwordResetService.validatePasswordResetToken(token);

        return ResponseEntity.ok("Hasło zostało zresetowane!");
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails handleBadCredentialsException(ApiException exception) {
        return ErrorDetails.builder()
                .exceptionClass(exception.getClass().getSimpleName())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .errorMessage(exception.getMessage())
                .build();
    }
}
