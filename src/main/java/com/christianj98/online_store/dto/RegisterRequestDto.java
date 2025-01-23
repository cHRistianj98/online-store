package com.christianj98.online_store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequestDto(

        @Email(message = "The email must be valid")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter.")
        @Pattern(regexp = ".*[!@#$%^&*(),.?\":{}|<>].*", message = "Password must contain at least one special character: !@#$%^&*(),.?\":{}|<>")
        @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit.")
        String password
) {
}
