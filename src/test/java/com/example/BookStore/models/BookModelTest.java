package com.example.BookStore.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookModelTest {

    @Test
    public void testCreateBook() {
        BookModel book = new BookModel();
        book.setName("Game of Thrones");
        book.setPrice(BigDecimal.valueOf(80.00));
        book.setDescription("An incredible world!");
        book.setCategory("Fantasy");
        book.setBookType("physical");

        assertEquals("Game of Thrones", book.getName());
        assertEquals(BigDecimal.valueOf(80.00), book.getPrice());
        assertEquals("An incredible world!", book.getDescription());
        assertEquals("Fantasy", book.getCategory());
        assertEquals("physical", book.getBookType());
    }
}
