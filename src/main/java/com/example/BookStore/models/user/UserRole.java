package com.example.BookStore.models.user;

public enum UserRole {
    ADMIN("admin"),
    CUSTOMER("customer");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
