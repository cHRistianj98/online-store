package com.christianj98.online_store.service.implementation;

import com.christianj98.online_store.dto.RegisterRequestDto;
import com.christianj98.online_store.exception.ApiException;
import com.christianj98.online_store.jwt.JwtTokenUtil;
import com.christianj98.online_store.repository.UserRepository;
import com.christianj98.online_store.service.email.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private EmailService emailService;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    public UserServiceImpl userService;

    @Test
    public void shouldThrowExceptionWhenEmailIsRegistered() {
        // given
        final var registerRequestDto = RegisterRequestDto.builder()
                .email("email@gmail.com")
                .build();
        when(userRepository.getEmailCount(registerRequestDto.email())).thenReturn(1);

        // when / then
        assertThatThrownBy(() -> userService.createUser(registerRequestDto))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining(registerRequestDto.email());
    }
}
