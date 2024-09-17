package com.example.BookStore.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "login cannot be blank") String login,
        @NotBlank(message = "password cannot be blank") String password
)
{ }
