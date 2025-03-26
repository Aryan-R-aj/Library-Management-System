package org.library;
import java.sql.*;
import java.util.Scanner;
public class Library {

    // Method to add a book to MySQL
    public boolean addBook(Book book) {
        String query = "INSERT INTO books (book_id, title, author, genre, is_available) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, book.getBookId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getGenre());
            pstmt.setBoolean(5, book.isAvailable());

            pstmt.executeUpdate();
            System.out.println("Book added successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding book!");
            e.printStackTrace();
            return false;
        }
    }

    // Method to view all books
    public void viewAllBooks() {
        String query = "SELECT * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No books available in the library.");
                return;
            }

            System.out.println("\n Library Books:");
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getString("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Genre: " + rs.getString("genre"));
                System.out.println("Availability: " + (rs.getBoolean("is_available") ? "Available" : "Checked Out"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books!");
            e.printStackTrace();
        }
    }

    // Method to search for a book by ID or Title
    public void searchBook(String searchKey) {
        String query = "SELECT * FROM books WHERE book_id = ? OR title LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, searchKey);
            pstmt.setString(2, "%" + searchKey + "%");
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("🔍 No matching book found.");
                return;
            }

            while (rs.next()) {
                System.out.println("Found Book:");
                System.out.println("Book ID: " + rs.getString("book_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Genre: " + rs.getString("genre"));
                System.out.println("Availability: " + (rs.getBoolean("is_available") ? "Available" : "Checked Out"));
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error searching book!");
            e.printStackTrace();
        }
    }

    // Method to update book details
    public boolean updateBook(String bookId, String title, String author, String genre, boolean isAvailable) {
        String query = "UPDATE books SET title = ?, author = ?, genre = ?, is_available = ? WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, genre);
            pstmt.setBoolean(4, isAvailable);
            pstmt.setString(5, bookId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Book updated successfully!");
                return true;
            } else {
                System.out.println("Book not found!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error updating book!");
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete a book from MySQL
    public boolean deleteBook(String bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, bookId);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Book deleted successfully!");
                return true;
            } else {
                System.out.println("Book not found!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book!");
            e.printStackTrace();
            return false;
        }
    }
}
