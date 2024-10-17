package library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI {

    private LibraryManager manager;
    private JTable bookTable;

    public LibraryGUI(LibraryManager manager) {
        this.manager = manager;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel for the table
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the JTable
        bookTable = new JTable();
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

        // Add action listeners for buttons
        setupButtonActions(viewButton, addButton, updateButton, deleteButton);

        frame.setVisible(true);
    }

    private void setupButtonActions(JButton viewButton, JButton addButton, JButton updateButton, JButton deleteButton) {
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableModel = manager.getAllBooks();
                bookTable.setModel(tableModel);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Title:");
                String author = JOptionPane.showInputDialog("Enter Author:");
                String publisher = JOptionPane.showInputDialog("Enter Publisher:");
                String yearStr = JOptionPane.showInputDialog("Enter Year:");

                try {
                    int year = Integer.parseInt(yearStr);
                    Book book = new Book(0, title, author, publisher, year);
                    manager.addBook(book);
                    JOptionPane.showMessageDialog(null, "Book added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid year format!");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updateIdStr = JOptionPane.showInputDialog("Enter Book ID to update:");
                int updateId;
                try {
                    updateId = Integer.parseInt(updateIdStr);
                    String newTitle = JOptionPane.showInputDialog("Enter new Title:");
                    String newAuthor = JOptionPane.showInputDialog("Enter new Author:");
                    String newPublisher = JOptionPane.showInputDialog("Enter new Publisher:");
                    String newYearStr = JOptionPane.showInputDialog("Enter new Year:");
                    int newYear = Integer.parseInt(newYearStr);
                    
                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newPublisher, newYear);
                    manager.updateBook(updatedBook);
                    JOptionPane.showMessageDialog(null, "Book updated successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteIdStr = JOptionPane.showInputDialog("Enter Book ID to delete:");
                try {
                    int deleteId = Integer.parseInt(deleteIdStr);
                    manager.deleteBook(deleteId);
                    JOptionPane.showMessageDialog(null, "Book deleted successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Book ID!");
                }
            }
        });
    }
}
