package com.example.BookStore.controllers;

import com.example.BookStore.dtos.BookDTO;
import com.example.BookStore.models.BookModel;
import com.example.BookStore.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "bookType", required = false) String bookType
    ) {
        List<BookModel> books = bookService.getAllBooks(page, size, category, bookType);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable(value="id") UUID id) {
        Optional<BookModel> bookO = bookService.getBookById(id);
        if(bookO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookO.get());
    }

    @PostMapping
    public ResponseEntity<BookModel> addBook(@RequestBody @Valid BookDTO bookDTO) {
        BookModel book = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable(value="id") UUID id, @RequestBody @Valid BookDTO bookDTO) {
        Optional<BookModel> bookO = bookService.getBookById(id);
        if(bookO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
        var bookModel = bookO.get();
        return  ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(bookModel, bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable(value="id") UUID id) {
        boolean isDeleted = bookService.deleteBook(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookModel>> searchBooks(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        List<BookModel> books = bookService.searchBooks(query, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
