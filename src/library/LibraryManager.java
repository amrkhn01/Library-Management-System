package library;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {

    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, publisher, year) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYear());
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ? WHERE book_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getYear());
            stmt.setInt(5, book.getBookId());
            stmt.executeUpdate();
            System.out.println("Book updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String query = "DELETE FROM books WHERE book_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Modified to return DefaultTableModel
    public DefaultTableModel getAllBooks() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Author");
        tableModel.addColumn("Publisher");
        tableModel.addColumn("Year");

        String query = "SELECT * FROM books";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("year")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableModel;
    }
}
