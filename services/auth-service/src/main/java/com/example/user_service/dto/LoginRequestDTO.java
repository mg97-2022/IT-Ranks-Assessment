package com.example.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @Email(message = "Please enter a valid email.")
    @NotBlank(message = "Email is required.")
    private String email;

    @NotNull(message = "Please enter your password.")
    private String password;
}
