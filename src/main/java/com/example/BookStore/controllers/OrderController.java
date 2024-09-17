package com.example.BookStore.controllers;

import com.example.BookStore.models.OrderModel;
import com.example.BookStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderModel> checkout(
            @RequestParam(value = "customerId") String customerId)
    {
        OrderModel order = orderService.checkout(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(order);

    }
}
