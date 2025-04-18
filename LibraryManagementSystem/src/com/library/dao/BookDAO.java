package com.library.dao;

import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    // SQL queries
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, isbn, publisher, publication_year, category, quantity, available_quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE book_id = ?";
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM books";
    private static final String UPDATE_BOOK = "UPDATE books SET title=?, author=?, isbn=?, publisher=?, publication_year=?, category=?, quantity=?, available_quantity=? WHERE book_id=?";
    private static final String DELETE_BOOK = "DELETE FROM books WHERE book_id=?";
    private static final String SEARCH_BOOKS = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";

    public void insertBook(Book book) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setString(6, book.getCategory());
            stmt.setInt(7, book.getQuantity());
            stmt.setInt(8, book.getAvailableQuantity());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    book.setBookId(rs.getInt(1));
                }
            }
        }
    }

    public Book selectBook(int bookId) throws SQLException {
        Book book = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BOOK_BY_ID)) {
            
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    book = new Book();
                    book.setBookId(rs.getInt("book_id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPublicationYear(rs.getInt("publication_year"));
                    book.setCategory(rs.getString("category"));
                    book.setQuantity(rs.getInt("quantity"));
                    book.setAvailableQuantity(rs.getInt("available_quantity"));
                }
            }
        }
        return book;
    }

    public List<Book> selectAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOOKS);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setCategory(rs.getString("category"));
                book.setQuantity(rs.getInt("quantity"));
                book.setAvailableQuantity(rs.getInt("available_quantity"));
                books.add(book);
            }
        }
        return books;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK)) {
            
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setString(6, book.getCategory());
            stmt.setInt(7, book.getQuantity());
            stmt.setInt(8, book.getAvailableQuantity());
            stmt.setInt(9, book.getBookId());
            
            rowUpdated = stmt.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteBook(int bookId) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BOOK)) {
            
            stmt.setInt(1, bookId);
            rowDeleted = stmt.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Book> searchBooks(String keyword) throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_BOOKS)) {
            
            String searchTerm = "%" + keyword + "%";
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            stmt.setString(3, searchTerm);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setBookId(rs.getInt("book_id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setPublisher(rs.getString("publisher"));
                    book.setPublicationYear(rs.getInt("publication_year"));
                    book.setCategory(rs.getString("category"));
                    book.setQuantity(rs.getInt("quantity"));
                    book.setAvailableQuantity(rs.getInt("available_quantity"));
                    books.add(book);
                }
            }
        }
        return books;
    }
}