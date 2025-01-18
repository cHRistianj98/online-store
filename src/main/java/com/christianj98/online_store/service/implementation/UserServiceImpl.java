package com.christianj98.online_store.service.implementation;

import com.christianj98.online_store.dto.LoginRequestDto;
import com.christianj98.online_store.dto.LoginResponseDto;
import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.entity.CustomUserDetails;
import com.christianj98.online_store.exception.ApiException;
import com.christianj98.online_store.jwt.JwtTokenUtil;
import com.christianj98.online_store.repository.UserRepository;
import com.christianj98.online_store.service.UserService;
import com.christianj98.online_store.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void createUser(RegisterRequestDto registerRequestDto) {
        if (isEmailAlreadyRegistered(registerRequestDto.email())) {
            throw new ApiException("Given email already exist in the system.");
        }
        final var user = new CustomUserDetails();
        user.setUsername(registerRequestDto.email());
        user.setPassword(passwordEncoder.encode(registerRequestDto.password()));
        user.setRole("ROLE_USER");
        emailService.sendEmail(registerRequestDto.email(), "Email verification", "Random message");
        userRepository.save(user);
    }

    public LoginResponseDto authenticate(LoginRequestDto loginRequest) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
            );
            String token = jwtTokenUtil.generateToken(authentication);
            return new LoginResponseDto(token);
    }

    private boolean isEmailAlreadyRegistered(String email) {
        return userRepository.getEmailCount(email) > 0;
    }
}
