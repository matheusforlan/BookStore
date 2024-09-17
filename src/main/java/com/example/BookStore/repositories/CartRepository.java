package com.example.BookStore.repositories;

import com.example.BookStore.models.CartModel;
import com.example.BookStore.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<CartModel, UUID> {

    Optional<CartModel> findByCustomer(UserModel userModel);
}
