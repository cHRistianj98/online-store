package com.christianj98.online_store.service.implementation;

import com.christianj98.online_store.entity.CustomUserDetails;
import com.christianj98.online_store.entity.PasswordResetToken;
import com.christianj98.online_store.repository.PasswordResetTokenRepository;
import com.christianj98.online_store.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender;

    public void createPasswordResetToken(CustomUserDetails user) {
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        tokenRepository.save(resetToken);

        sendResetEmail(user.getUsername(), token);
    }

    private void sendResetEmail(String email, String token) {
        final var resetUrl = "http://localhost:8080/api/auth/reset-password?token=" + token;
        final var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Reset hasła");
        mailMessage.setText("Aby zresetować hasło, kliknij w poniższy link:\n" + resetUrl);

        mailSender.send(mailMessage);
    }
}
