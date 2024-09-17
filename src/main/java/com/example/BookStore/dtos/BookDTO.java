package com.example.BookStore.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BookDTO(
        @NotBlank(message = "Name cannot be blank") String name,
        @NotBlank(message = "Description cannot be blank") String description,
        @NotNull(message = "Price cannot be null") @Positive(message = "Price must be positive") BigDecimal price,
        @NotBlank(message = "Category cannot be blank") String category,
        @NotBlank(message = "Book Type cannot be blank") String bookType
)
{ }
