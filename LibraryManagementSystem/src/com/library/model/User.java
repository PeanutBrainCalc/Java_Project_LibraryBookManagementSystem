package com.library.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    
    // Constructors, getters, and setters
    public User() {}
    
    public User(String username, String password, String name, 
                String email, String phone, String role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    // Getters and setters for all fields
    // ...
}