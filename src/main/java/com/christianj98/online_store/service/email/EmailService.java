package com.christianj98.online_store.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        final var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("no-reply@example.com");

        mailSender.send(message);
    }

    @Async
    public void sendResetEmail(String email, String resetUrl) {
        final var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Resetowanie hasła");
        mailMessage.setText("Aby zresetować hasło, kliknij w poniższy link:\n" + resetUrl
            + "\nJeśli nie inicjowałeś resetu hasła, zignoruj tę wiadomość.");

        mailSender.send(mailMessage);
    }
}
