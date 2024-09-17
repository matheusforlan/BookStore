package com.example.BookStore.services;

import com.example.BookStore.models.BookModel;
import com.example.BookStore.models.CartModel;
import com.example.BookStore.models.user.UserModel;
import com.example.BookStore.repositories.BookRepository;
import com.example.BookStore.repositories.CartRepository;
import com.example.BookStore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public CartModel addToCart(String customerId, UUID bookId) {
        Optional<UserModel> customer = userRepository.findById(customerId);
        Optional<BookModel> book = bookRepository.findById(bookId);

        if (customer.isPresent() && book.isPresent()) {
            CartModel cart = cartRepository.findByCustomer(customer.get())
                    .orElse(new CartModel(customer.get(), List.of()));
            cart.getBooks().add(book.get());
            return cartRepository.save(cart);
        }

        throw new RuntimeException("Customer or Book not found");
    }

    public CartModel getCartByCustomerId(String customerId) {
        UserModel customer = userRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        return cartRepository.findByCustomer(customer).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void clearCart(CartModel cart) {
        cart.getBooks().clear();
        cartRepository.save(cart);
    }
}
