package com.christianj98.online_store.controller;

import com.christianj98.online_store.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test-email")
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<String> sendTestEmail() {
        emailService.sendEmail(
                "test@example.com",
                "Test Email",
                "This is a test email sent via MailHog."
        );
        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}