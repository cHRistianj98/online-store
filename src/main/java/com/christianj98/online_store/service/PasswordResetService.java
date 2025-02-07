package com.christianj98.online_store.service;

public interface PasswordResetService {
    void createPasswordResetTokenForUser(String email, String appUrl);
    void validatePasswordResetToken(String token);
}
