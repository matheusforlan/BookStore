package com.example.BookStore.services;

import com.example.BookStore.dtos.BookDTO;
import com.example.BookStore.models.BookModel;
import com.example.BookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookModel> getAllBooks(int page, int size, String category, String bookType) {
        Pageable pageable = PageRequest.of(page, size);

        if (category != null && bookType != null) {
            return bookRepository.findByCategoryAndBookType(category, bookType, pageable).getContent();
        } else if (category != null) {
            return bookRepository.findByCategory(category, pageable).getContent();
        } else if (bookType != null) {
            return bookRepository.findByBookType(bookType, pageable).getContent();
        } else {
            return bookRepository.findAll(pageable).getContent();
        }
    }

    public Optional<BookModel> getBookById(UUID id) {
        return bookRepository.findById(id);
    }

    public BookModel addBook(BookDTO bookDTO) {
        BookModel book = new BookModel();
        book.setName(bookDTO.name());
        book.setPrice(bookDTO.price());
        book.setDescription(bookDTO.description());
        book.setCategory(bookDTO.category());
        book.setBookType(bookDTO.bookType());
        return bookRepository.save(book);
    }

    public BookModel updateBook(BookModel bookModel, BookDTO bookDTO) {

            bookModel.setName(bookDTO.name());
            bookModel.setPrice(bookDTO.price());
            bookModel.setDescription(bookDTO.description());
            bookModel.setCategory(bookDTO.category());
            bookModel.setBookType(bookDTO.bookType());

            return bookRepository.save(bookModel);
    }

    public boolean deleteBook(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<BookModel> searchBooks(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookModel> resultPage = bookRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable);
        return resultPage.getContent();
    }

}
