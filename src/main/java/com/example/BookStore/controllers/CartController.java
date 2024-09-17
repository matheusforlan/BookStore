package com.example.BookStore.controllers;

import com.example.BookStore.models.CartModel;
import com.example.BookStore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{bookId}")
    public ResponseEntity<CartModel> addToCart(
            @RequestParam(value = "customerId") String customerId,
            @PathVariable(value="bookId") UUID bookId) {
        CartModel updatedCart = cartService.addToCart(customerId, bookId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCart);
    }

    @GetMapping
    public ResponseEntity<CartModel> getCart(
            @RequestParam(value = "customerId") String customerId
    ) {
        CartModel cart = cartService.getCartByCustomerId(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
}
