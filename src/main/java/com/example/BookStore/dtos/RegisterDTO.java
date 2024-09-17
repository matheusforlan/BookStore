package com.example.BookStore.dtos;

import com.example.BookStore.models.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank(message = "login cannot be blank") String login,
        @NotBlank(message = "password cannot be blank") String password,
        @NotNull(message = "role cannot be blank") UserRole role
) {
}
