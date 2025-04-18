package com.library.model;

import java.time.LocalDate;

public class Borrow {
    private int borrowingId;
    private int bookId;
    private int userId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private Book book;
    private User user;
    
    // Constructors, getters, and setters
    public Borrow() {}
    
    public Borrow(int bookId, int userId, LocalDate borrowDate, 
                  LocalDate dueDate, String status) {
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and setters for all fields
    // ...
}