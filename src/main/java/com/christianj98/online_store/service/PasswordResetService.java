package com.christianj98.online_store.service;

import com.christianj98.online_store.entity.CustomUserDetails;

public interface PasswordResetService {
    void createPasswordResetTokenForUser(String email, String appUrl);
}
