package com.christianj98.online_store.controller;

import com.christianj98.online_store.repository.UserRepository;
import com.christianj98.online_store.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/verify")
public class EmailController {

    private final EmailService emailService;
    private final UserRepository userRepository;

    @GetMapping("/account/{verificationKey}")
    @Transactional
    public ResponseEntity<String> verifyUserAccount(@PathVariable String verificationKey) {
        userRepository.findByEmailVerificationToken(verificationKey)
                .ifPresent(user -> user.setEnabled(true));
        return new ResponseEntity<>("Account is verified", HttpStatus.OK);
    }
}
