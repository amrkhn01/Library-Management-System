package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        LibraryManager manager = new LibraryManager();
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel for the table
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the JTable
        JTable bookTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(bookTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        JButton viewButton = new JButton("View All Books");
        JButton addButton = new JButton("Add Book");
        JButton updateButton = new JButton("Update Book");
        JButton deleteButton = new JButton("Delete Book");
        
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        
        panel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(panel);

        // Action for View All Books button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = manager.getAllBooks();
                bookTable.setModel(tableModel);
            }
        });

        // Action for Add Book button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gather book details from user input
                String title = JOptionPane.showInputDialog(frame, "Enter Title:");
                String author = JOptionPane.showInputDialog(frame, "Enter Author:");
                String publisher = JOptionPane.showInputDialog(frame, "Enter Publisher:");
                String yearStr = JOptionPane.showInputDialog(frame, "Enter Year:");

                try {
                    int year = Integer.parseInt(yearStr);
                    Book book = new Book(0, title, author, publisher, year);
                    manager.addBook(book);
                    JOptionPane.showMessageDialog(frame, "Book added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid year format!");
                }
            }
        });

        // Action for Update Book button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateIdStr = JOptionPane.showInputDialog(frame, "Enter Book ID to update:");
                int updateId;
                try {
                    updateId = Integer.parseInt(updateIdStr);
                    String newTitle = JOptionPane.showInputDialog(frame, "Enter new Title:");
                    String newAuthor = JOptionPane.showInputDialog(frame, "Enter new Author:");
                    String newPublisher = JOptionPane.showInputDialog(frame, "Enter new Publisher:");
                    String newYearStr = JOptionPane.showInputDialog(frame, "Enter new Year:");
                    int newYear = Integer.parseInt(newYearStr);
                    
                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newPublisher, newYear);
                    manager.updateBook(updatedBook);
                    JOptionPane.showMessageDialog(frame, "Book updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            }
        });

        // Action for Delete Book button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteIdStr = JOptionPane.showInputDialog(frame, "Enter Book ID to delete:");
                try {
                    int deleteId = Integer.parseInt(deleteIdStr);
                    manager.deleteBook(deleteId);
                    JOptionPane.showMessageDialog(frame, "Book deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid Book ID!");
                }
            }
        });

        frame.setVisible(true);
    }
}
