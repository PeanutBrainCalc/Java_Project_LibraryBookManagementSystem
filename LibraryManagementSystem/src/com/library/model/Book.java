package com.library.model;

import java.time.LocalDate;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int publicationYear;
    private String category;
    private int quantity;
    private int availableQuantity;
    private LocalDate createdAt;

    // Constructors, getters, and setters
    public Book() {}
    
    public Book(String title, String author, String isbn, String publisher, 
                int publicationYear, String category, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    // Getters and setters for all fields
    // ...
}