package com.christianj98.online_store.service;

import com.christianj98.online_store.dto.ForgotPasswordRequest;
import com.christianj98.online_store.dto.LoginRequestDto;
import com.christianj98.online_store.dto.LoginResponseDto;
import com.christianj98.online_store.dto.RegisterRequestDto;

public interface UserService {
    void createUser(RegisterRequestDto registerRequestDto);
    LoginResponseDto authenticate(LoginRequestDto loginRequest);
}
