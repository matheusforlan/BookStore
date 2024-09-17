package com.example.BookStore.repositories;

import com.example.BookStore.models.BookModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID> {

    Page<BookModel> findByCategoryAndBookType(String category, String bookType, Pageable pageable);

    Page<BookModel> findByCategory(String category, Pageable pageable);

    Page<BookModel> findByBookType(String bookType, Pageable pageable);

    @Override
    Page<BookModel> findAll(Pageable pageable);

    // Search by name or description using "contains" (LIKE) and ignoring secret or lowercase letters (IGNORE CASE)
    Page<BookModel> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description, Pageable pageable);

}
