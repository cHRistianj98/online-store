package com.christianj98.online_store.controller;

import com.christianj98.online_store.dto.LoginRequestDto;
import com.christianj98.online_store.dto.LoginResponseDto;
import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
