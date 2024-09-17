package com.example.BookStore.models;

import com.example.BookStore.models.user.UserModel;
import com.example.BookStore.models.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserModelTest {

    @Test
    public void testCreateUserWithCart() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        UserModel customer = new UserModel("forlanCustomer", encoder.encode("123456"), UserRole.CUSTOMER);
        CartModel cart = new CartModel(customer);
        customer.setCart(cart);

        assertEquals("forlanCustomer", customer.getLogin());
        assertTrue(encoder.matches("123456", customer.getPassword()));
        assertEquals(UserRole.CUSTOMER, customer.getRole());
        assertEquals(cart, customer.getCart());
    }
}
