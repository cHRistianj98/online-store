package com.christianj98.online_store.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class ResetPasswordRequest {
    private String token;
    private String password;
}
