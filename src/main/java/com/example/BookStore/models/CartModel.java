package com.example.BookStore.models;

import com.example.BookStore.models.user.UserModel;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private UserModel customer;

    @ManyToMany
    @JoinTable(name = "cart_books",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<BookModel> books = new ArrayList<>();

    public CartModel() {
        this.books = new ArrayList<>();
    }

    public CartModel(UserModel customer) {
        this.customer = customer;
        this.books = new ArrayList<>();
    }

    public CartModel(UserModel customer, List<BookModel> books) {
        this.customer = customer;
        this.books = books;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserModel getCustomer() {
        return customer;
    }

    public void setCustomer(UserModel customer) {
        this.customer = customer;
    }

    public List<BookModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
    }
}