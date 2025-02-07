package com.christianj98.online_store.service.implementation;

import com.christianj98.online_store.entity.CustomUserDetails;
import com.christianj98.online_store.entity.PasswordResetToken;
import com.christianj98.online_store.repository.PasswordResetTokenRepository;
import com.christianj98.online_store.repository.UserRepository;
import com.christianj98.online_store.service.PasswordResetService;
import com.christianj98.online_store.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final EmailService emailService;
    private final JavaMailSender mailSender;
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    @Transactional
    public void createPasswordResetTokenForUser(String email, String appUrl) {
        userRepository.findByUsername(email)
                .ifPresent(user -> {
                    final var token = UUID.randomUUID().toString();
                    final var passwordResetToken = new PasswordResetToken(
                            token,
                            user,
                            LocalDateTime.now().plusMinutes(60)
                    );
                    tokenRepository.save(passwordResetToken);
                    String resetUrl = appUrl + "/reset-password?token=" + token;
                    emailService.sendResetEmail(resetUrl, token);
                });
    }
}
