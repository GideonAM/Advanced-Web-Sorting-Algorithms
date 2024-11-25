package com.example.advanced_web_sorting_algorithms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationDto (
        @NotBlank(message = "User email is required")
        @Email(message = "Enter a valid email", regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
        String email,

        @NotBlank(message = "User password is required")
        @Pattern(regexp = "[a-zA-Z0-9]{3,20}", message = "Password should contain letters and numbers only")
        String password
) {
}
