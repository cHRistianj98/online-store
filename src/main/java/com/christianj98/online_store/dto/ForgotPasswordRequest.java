package com.christianj98.online_store.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ForgotPasswordRequest {
    @Email
    private String email;

}
