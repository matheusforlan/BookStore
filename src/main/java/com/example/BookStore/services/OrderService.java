package com.example.BookStore.services;

import com.example.BookStore.models.BookModel;
import com.example.BookStore.models.CartModel;
import com.example.BookStore.models.OrderModel;
import com.example.BookStore.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;

    public OrderModel checkout(String customerId) {
        CartModel cart = cartService.getCartByCustomerId(customerId);

        double totalPrice = cart.getBooks().stream()
                .mapToDouble(book -> book.getPrice().doubleValue())
                .sum();

        List<BookModel> clonedBooks = new ArrayList<>(cart.getBooks());

        OrderModel order = new OrderModel(cart.getCustomer(), clonedBooks, totalPrice, "COMPLETED");
        cartService.clearCart(cart);  // Clear the cart after checkout
        return orderRepository.save(order);
    }
}

