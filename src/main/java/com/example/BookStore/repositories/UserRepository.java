package com.example.BookStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BookStore.models.user.UserModel;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, String> {
    UserDetails findByLogin(String login);
}
