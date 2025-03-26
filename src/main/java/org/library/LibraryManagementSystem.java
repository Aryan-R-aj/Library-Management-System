package org.library;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability (true for Available, false for Checked Out): ");
                    boolean availability = scanner.nextBoolean();

                    Book newBook = new Book(bookId, title, author, genre, availability);
                    library.addBook(newBook);
                    break;

                case 2:
                    library.viewAllBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID or Title to search: ");
                    String searchKey = scanner.nextLine();
                    library.searchBook(searchKey);
                    break;

                case 4:
                    System.out.print("✏ Enter Book ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("✏ New Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("✏ New Author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("✏ New Genre: ");
                    String newGenre = scanner.nextLine();
                    System.out.print("✏ New Availability (true/false): ");
                    boolean newAvailability = scanner.nextBoolean();

                    library.updateBook(updateId, newTitle, newAuthor, newGenre, newAvailability);
                    break;

                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    String deleteId = scanner.nextLine();
                    library.deleteBook(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }
}
