package com.example.BookStore.services;

import com.example.BookStore.models.BookModel;
import com.example.BookStore.models.CartModel;
import com.example.BookStore.models.OrderModel;
import com.example.BookStore.models.user.UserModel;
import com.example.BookStore.models.user.UserRole;
import com.example.BookStore.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private CartService cartService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testCheckout() {
        // Arrange
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserModel customer = new UserModel("forlanCustomer", encoder.encode("123456"), UserRole.CUSTOMER);
        CartModel cart = new CartModel(customer);
        BookModel book1 = new BookModel();
        book1.setName("Book 1");
        book1.setPrice(BigDecimal.valueOf(50.0));
        book1.setDescription("Description");
        book1.setCategory("Fantasy");
        book1.setBookType("physical");
        BookModel book2 = new BookModel();
        book2.setName("Book 2");
        book2.setPrice(BigDecimal.valueOf(30.0));
        book2.setDescription("Description");
        book2.setCategory("Fantasy");
        book2.setBookType("digital");
        cart.getBooks().add(book1);
        cart.getBooks().add(book2);

        when(cartService.getCartByCustomerId(anyString())).thenReturn(cart);

        OrderModel savedOrder = new OrderModel();
        savedOrder.setCustomer(customer);
        savedOrder.setBooks(cart.getBooks());
        savedOrder.setTotalPrice(80.00);
        savedOrder.setStatus("COMPLETED");

        when(orderRepository.save(any(OrderModel.class))).thenReturn(savedOrder);

        // Act
        OrderModel order = orderService.checkout(customer.getLogin());

        // Assert
        assertEquals(80.00, order.getTotalPrice());
        assertEquals(2, order.getBooks().size());
        assertEquals("COMPLETED", order.getStatus());
        verify(cartService).clearCart(cart); // Verify if the cart is clean
    }
}
