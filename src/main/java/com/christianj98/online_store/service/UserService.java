package com.christianj98.online_store.service;

import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.entity.CustomUserDetails;
import com.christianj98.online_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(RegisterRequestDto registerRequestDto) {
        CustomUserDetails user = new CustomUserDetails();
        user.setUsername(registerRequestDto.username());
        user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}
