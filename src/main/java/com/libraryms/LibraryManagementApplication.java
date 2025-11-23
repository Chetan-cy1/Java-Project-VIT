package com.libraryms;

import com.libraryms.model.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Simplified Library Management Application with only 3 core modules:
 * 1. Book Management
 * 2. Member Management  
 * 3. Transaction Management
 */
public class LibraryManagementApplication {

    private final Scanner scanner;
    private final List<SimpleBook> books;
    private final List<SimpleMember> members;
    private final List<SimpleTransaction> transactions;
    private int nextMemberId = 1;
    private int nextTransactionId = 1;

    public LibraryManagementApplication() {
        this.scanner = new Scanner(System.in);
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new ArrayList<>();
        initializeWithSampleData();
    }

    private void initializeWithSampleData() {
        SimpleBook book1 = new SimpleBook("978-0134685991", "Effective Java", "Joshua Bloch", BookCategory.COMPUTER_PROGRAMMING);
        book1.setPublisher("Addison-Wesley");
        book1.setYearPublished(2017);
        books.add(book1);

        SimpleBook book2 = new SimpleBook("978-0321356680", "Clean Code", "Robert Martin", BookCategory.COMPUTER_PROGRAMMING);
        book2.setPublisher("Prentice Hall");
        book2.setYearPublished(2008);
        books.add(book2);

        SimpleMember member1 = new SimpleMember("MEM" + String.format("%06d", nextMemberId++), 
                                               "John", "Doe", "john.doe@email.com", MemberType.STUDENT);
        member1.setPhoneNumber("+1-555-0101");
        members.add(member1);

        SimpleMember member2 = new SimpleMember("MEM" + String.format("%06d", nextMemberId++), 
                                               "Jane", "Smith", "jane.smith@email.com", MemberType.FACULTY);
        member2.setPhoneNumber("+1-555-0102");
        members.add(member2);
    }

    public static void main(String[] args) {
        System.out.println("Starting Digital Library Management System...");
        
        LibraryManagementApplication app = new LibraryManagementApplication();
        app.run();
        
        System.out.println("Digital Library Management System shutting down.");
    }

    public void run() {
        printWelcomeBanner();
        
        boolean running = true;
        while (running) {
            try {
                printMainMenu();
                int choice = readIntegerInput("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        handleBookManagement();
                        break;
                    case 2:
                        handleMemberManagement();
                        break;
                    case 3:
                        handleTransactionManagement();
                        break;
                    case 0:
                        System.out.println("Thank you for using the Library Management System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void printWelcomeBanner() {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║              Digital Library Management System                ║");
        System.out.println("║                     Version 1.0.0                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private void printMainMenu() {
        System.out.println("┌─────────────────── MAIN MENU ───────────────────┐");
        System.out.println("│  1. Book Management                             │");
        System.out.println("│  2. Member Management                           │");
        System.out.println("│  3. Transaction Management                      │");
        System.out.println("│  0. Exit                                        │");
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    // =================== BOOK MANAGEMENT ===================
    
    private void handleBookManagement() {
        boolean backToMain = false;
        while (!backToMain) {
            printBookManagementMenu();
            int choice = readIntegerInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        searchBooks();
                        break;
                    case 3:
                        viewAllBooks();
                        break;
                    case 4:
                        updateBookInfo();
                        break;
                    case 5:
                        deleteBook();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printBookManagementMenu() {
        System.out.println("┌───────────── BOOK MANAGEMENT ─────────────┐");
        System.out.println("│  1. Add New Book                          │");
        System.out.println("│  2. Search Books                          │");
        System.out.println("│  3. View All Books                        │");
        System.out.println("│  4. Update Book Information               │");
        System.out.println("│  5. Delete Book                           │");
        System.out.println("│  0. Back to Main Menu                     │");
        System.out.println("└───────────────────────────────────────────┘");
    }

    private void addNewBook() {
        System.out.println("\\n=== Add New Book ===");
        
        try {
            String isbn = readStringInput("Enter ISBN: ");
            
            // Check for duplicate ISBN
            if (findBookByIsbn(isbn) != null) {
                System.out.println("A book with this ISBN already exists!");
                return;
            }
            
            String title = readStringInput("Enter Title: ");
            String author = readStringInput("Enter Author: ");
            
            System.out.println("Available categories:");
            BookCategory[] categories = BookCategory.values();
            for (int i = 0; i < categories.length; i++) {
                System.out.printf("  %d. %s%n", i + 1, categories[i].getDisplayName());
            }
            
            int categoryChoice = readIntegerInput("Select category (1-" + categories.length + "): ");
            if (categoryChoice < 1 || categoryChoice > categories.length) {
                System.out.println("Invalid category selection");
                return;
            }
            BookCategory category = categories[categoryChoice - 1];
            
            SimpleBook book = new SimpleBook(isbn, title, author, category);
            
            // Optional fields
            String publisher = readStringInput("Enter Publisher (optional): ");
            if (!publisher.trim().isEmpty()) {
                book.setPublisher(publisher);
            }
            
            Integer year = readOptionalIntegerInput("Enter Publication Year (optional): ");
            if (year != null) {
                book.setYearPublished(year);
            }
            
            books.add(book);
            System.out.println("✓ Book added successfully!");
            System.out.println("Book Details: " + book);
            
        } catch (Exception e) {
            System.out.println("Failed to add book: " + e.getMessage());
        }
    }

    private void searchBooks() {
        System.out.println("=== Search Books ===");
        String query = readStringInput("Enter search term (title, author, or ISBN): ");
        
        List<SimpleBook> results = new ArrayList<>();
        String searchTerm = query.toLowerCase();
        
        for (SimpleBook book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm) ||
                book.getAuthor().toLowerCase().contains(searchTerm) ||
                book.getIsbn().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No books found matching your search.");
        } else {
            System.out.println("Found " + results.size() + " book(s):");
            displayBooks(results);
        }
    }

    private void viewAllBooks() {
        System.out.println("=== All Books ===");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Total books: " + books.size());
            displayBooks(books);
        }
    }

    private void displayBooks(List<SimpleBook> bookList) {
        System.out.println("┌────────────────┬──────────────────────────┬────────────────────┬─────────────────┬──────────────┐");
        System.out.println("│ ISBN           │ Title                    │ Author             │ Category        │ Status       │");
        System.out.println("├────────────────┼──────────────────────────┼────────────────────┼─────────────────┼──────────────┤");
        
        for (SimpleBook book : bookList) {
            System.out.printf("│ %-14s │ %-24s │ %-18s │ %-15s │ %-12s │%n",
                truncate(book.getIsbn(), 14),
                truncate(book.getTitle(), 24),
                truncate(book.getAuthor(), 18),
                truncate(book.getCategory().getDisplayName(), 15),
                book.getStatus().getDisplayName()
            );
        }
        System.out.println("└────────────────┴──────────────────────────┴────────────────────┴─────────────────┴──────────────┘");
    }

    private void updateBookInfo() {
        System.out.println("\\n=== Update Book Information ===");
        String isbn = readStringInput("Enter ISBN of book to update: ");
        
        SimpleBook book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        
        System.out.println("Current book details: " + book);
        
        System.out.println("\\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Publisher");
        System.out.println("0. Cancel");
        
        int choice = readIntegerInput("Enter choice: ");
        
        switch (choice) {
            case 1:
                String newTitle = readStringInput("Enter new title: ");
                book.setTitle(newTitle);
                System.out.println("✓ Title updated successfully!");
                break;
            case 2:
                String newAuthor = readStringInput("Enter new author: ");
                book.setAuthor(newAuthor);
                System.out.println("✓ Author updated successfully!");
                break;
            case 3:
                String newPublisher = readStringInput("Enter new publisher: ");
                book.setPublisher(newPublisher);
                System.out.println("✓ Publisher updated successfully!");
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void deleteBook() {
        System.out.println("\\n=== Delete Book ===");
        String isbn = readStringInput("Enter ISBN of book to delete: ");
        
        SimpleBook book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        
        System.out.println("Book to delete: " + book);
        
        String confirmation = readStringInput("Are you sure you want to delete this book? (yes/no): ");
        if ("yes".equalsIgnoreCase(confirmation)) {
            books.remove(book);
            System.out.println("✓ Book deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private SimpleBook findBookByIsbn(String isbn) {
        for (SimpleBook book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // =================== MEMBER MANAGEMENT ===================
    
    private void handleMemberManagement() {
        boolean backToMain = false;
        while (!backToMain) {
            printMemberManagementMenu();
            int choice = readIntegerInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        registerNewMember();
                        break;
                    case 2:
                        searchMembers();
                        break;
                    case 3:
                        viewAllMembers();
                        break;
                    case 4:
                        updateMemberInfo();
                        break;
                    case 5:
                        viewMemberDetails();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMemberManagementMenu() {
        System.out.println("┌─────────── MEMBER MANAGEMENT ──────────┐");
        System.out.println("│  1. Register New Member                │");
        System.out.println("│  2. Search Members                     │");
        System.out.println("│  3. View All Members                   │");
        System.out.println("│  4. Update Member Information          │");
        System.out.println("│  5. View Member Details                │");
        System.out.println("│  0. Back to Main Menu                  │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void registerNewMember() {
        System.out.println("\\n=== Register New Member ===");
        
        try {
            String firstName = readStringInput("Enter First Name: ");
            String lastName = readStringInput("Enter Last Name: ");
            String email = readStringInput("Enter Email: ");
            
            // Check for duplicate email
            for (SimpleMember existingMember : members) {
                if (existingMember.getEmail().equalsIgnoreCase(email)) {
                    System.out.println("A member with this email already exists!");
                    return;
                }
            }
            
            System.out.println("Available member types:");
            MemberType[] types = MemberType.values();
            for (int i = 0; i < types.length; i++) {
                MemberType type = types[i];
                System.out.printf("  %d. %s - %s%n",
                        i + 1, type.getDisplayName(), type.getDescription());
            }
            
            int typeChoice = readIntegerInput("Select member type (1-" + types.length + "): ");
            if (typeChoice < 1 || typeChoice > types.length) {
                System.out.println("Invalid member type selection");
                return;
            }
            MemberType memberType = types[typeChoice - 1];
            
            String memberId = "MEM" + String.format("%06d", nextMemberId++);
            SimpleMember member = new SimpleMember(memberId, firstName, lastName, email, memberType);
            
            String phone = readStringInput("Enter Phone Number (optional): ");
            if (!phone.trim().isEmpty()) {
                member.setPhoneNumber(phone);
            }
            
            members.add(member);
            System.out.println("✓ Member registered successfully!");
            System.out.println("Member Details: " + member);
            
        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }

    private void searchMembers() {
        System.out.println("\\n=== Search Members ===");
        String query = readStringInput("Enter search term (name, member ID, or email): ");
        
        List<SimpleMember> results = new ArrayList<>();
        String searchTerm = query.toLowerCase();
        
        for (SimpleMember member : members) {
            if (member.getFirstName().toLowerCase().contains(searchTerm) ||
                member.getLastName().toLowerCase().contains(searchTerm) ||
                member.getMemberId().toLowerCase().contains(searchTerm) ||
                (member.getEmail() != null && member.getEmail().toLowerCase().contains(searchTerm))) {
                results.add(member);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No members found matching your search.");
        } else {
            System.out.println("Found " + results.size() + " member(s):");
            displayMembers(results);
        }
    }

    private void viewAllMembers() {
        System.out.println("\\n=== All Members ===");
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            System.out.println("Total members: " + members.size());
            displayMembers(members);
        }
    }

    private void displayMembers(List<SimpleMember> memberList) {
        System.out.println("┌────────────┬─────────────────────────┬─────────────────────────┬──────────────┬──────────┐");
        System.out.println("│ Member ID  │ Name                    │ Email                   │ Type         │ Status   │");
        System.out.println("├────────────┼─────────────────────────┼─────────────────────────┼──────────────┼──────────┤");
        
        for (SimpleMember member : memberList) {
            System.out.printf("│ %-10s │ %-23s │ %-23s │ %-12s │ %-8s │%n",
                member.getMemberId(),
                truncate(member.getFullName(), 23),
                truncate(member.getEmail() != null ? member.getEmail() : "", 23),
                truncate(member.getMemberType().getDisplayName(), 12),
                member.getStatus().getDisplayName()
            );
        }
        System.out.println("└────────────┴─────────────────────────┴─────────────────────────┴──────────────┴──────────┘");
    }

    private void updateMemberInfo() {
        System.out.println("\\n=== Update Member Information ===");
        String memberId = readStringInput("Enter Member ID to update: ");
        
        SimpleMember member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }
        
        System.out.println("Current member details: " + member);
        
        System.out.println("\\nWhat would you like to update?");
        System.out.println("1. Phone Number");
        System.out.println("2. Address");
        System.out.println("0. Cancel");
        
        int choice = readIntegerInput("Enter choice: ");
        
        switch (choice) {
            case 1:
                String newPhone = readStringInput("Enter new phone number: ");
                member.setPhoneNumber(newPhone);
                System.out.println("✓ Phone number updated successfully!");
                break;
            case 2:
                String newAddress = readStringInput("Enter new address: ");
                member.setAddress(newAddress);
                System.out.println("✓ Address updated successfully!");
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private void viewMemberDetails() {
        System.out.println("\\n=== View Member Details ===");
        String memberId = readStringInput("Enter Member ID: ");
        
        SimpleMember member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }
        
        System.out.println("\\n=== Member Details ===");
        System.out.println("ID: " + member.getMemberId());
        System.out.println("Name: " + member.getFullName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Phone: " + (member.getPhoneNumber() != null ? member.getPhoneNumber() : "Not provided"));
        System.out.println("Address: " + (member.getAddress() != null ? member.getAddress() : "Not provided"));
        System.out.println("Type: " + member.getMemberType().getDisplayName());
        System.out.println("Status: " + member.getStatus().getDisplayName());
        System.out.println("Membership End: " + member.getMembershipEndDate());
        System.out.println("Current Borrowed Books: " + member.getCurrentBorrowedBooks() + "/" + member.getMemberType().getMaxBooksAllowed());
        System.out.println("Total Fines Owed: $" + String.format("%.2f", member.getTotalFinesOwed()));
        System.out.println("Can Borrow Books: " + (member.canBorrowBooks() ? "Yes" : "No"));
    }

    private SimpleMember findMemberById(String memberId) {
        for (SimpleMember member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    // =================== TRANSACTION MANAGEMENT ===================
    
    private void handleTransactionManagement() {
        boolean backToMain = false;
        while (!backToMain) {
            printTransactionManagementMenu();
            int choice = readIntegerInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1:
                        borrowBook();
                        break;
                    case 2:
                        returnBook();
                        break;
                    case 3:
                        viewTransactionHistory();
                        break;
                    case 4:
                        viewOverdueBooks();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printTransactionManagementMenu() {
        System.out.println("┌──────── TRANSACTION MANAGEMENT ────────┐");
        System.out.println("│  1. Borrow Book                        │");
        System.out.println("│  2. Return Book                        │");
        System.out.println("│  3. View Transaction History           │");
        System.out.println("│  4. View Overdue Books                 │");
        System.out.println("│  0. Back to Main Menu                  │");
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void borrowBook() {
        System.out.println("\\n=== Borrow Book ===");
        
        String memberId = readStringInput("Enter Member ID: ");
        SimpleMember member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }
        
        if (!member.canBorrowBooks()) {
            System.out.println("Member cannot borrow books. Reasons could be:");
            System.out.println("- Membership expired");
            System.out.println("- Already borrowed maximum books (" + member.getCurrentBorrowedBooks() + "/" + member.getMemberType().getMaxBooksAllowed() + ")");
            System.out.println("- Outstanding fines too high ($" + String.format("%.2f", member.getTotalFinesOwed()) + ")");
            return;
        }
        
        String isbn = readStringInput("Enter Book ISBN: ");
        SimpleBook book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is not available for borrowing. Status: " + book.getStatus().getDisplayName());
            return;
        }
        
        // Create transaction
        String transactionId = "TXN" + String.format("%06d", nextTransactionId++);
        SimpleTransaction transaction = new SimpleTransaction(transactionId, memberId, isbn, TransactionType.BORROW);
        
        // Update book and member
        book.borrowCopy();
        member.borrowBook();
        
        transactions.add(transaction);
        
        System.out.println("✓ Book borrowed successfully!");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Due Date: " + transaction.getDueDate());
        System.out.println("Member: " + member.getFullName() + " (" + member.getCurrentBorrowedBooks() + "/" + member.getMemberType().getMaxBooksAllowed() + " books)");
    }

    private void returnBook() {
        System.out.println("\\n=== Return Book ===");
        
        String memberId = readStringInput("Enter Member ID: ");
        SimpleMember member = findMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return;
        }
        
        String isbn = readStringInput("Enter Book ISBN: ");
        SimpleBook book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return;
        }
        
        // Find active borrowing transaction
        SimpleTransaction borrowTransaction = null;
        for (SimpleTransaction transaction : transactions) {
            if (transaction.getMemberId().equals(memberId) &&
                transaction.getBookIsbn().equals(isbn) &&
                transaction.getType() == TransactionType.BORROW &&
                transaction.getStatus() == TransactionStatus.ACTIVE) {
                borrowTransaction = transaction;
                break;
            }
        }
        
        if (borrowTransaction == null) {
            System.out.println("No active borrowing transaction found for this member and book.");
            return;
        }
        
        // Complete the return
        borrowTransaction.completeReturn();
        book.returnCopy();
        member.returnBook();
        
        System.out.println("✓ Book returned successfully!");
        
        if (borrowTransaction.getFineAmount() > 0) {
            member.addFine(borrowTransaction.getFineAmount());
            System.out.println("⚠ Late return fine applied: $" + String.format("%.2f", borrowTransaction.getFineAmount()));
            System.out.println("Days overdue: " + borrowTransaction.getDaysOverdue());
            System.out.println("Total member fines: $" + String.format("%.2f", member.getTotalFinesOwed()));
        } else {
            System.out.println("✓ Returned on time - No fines applied");
        }
    }

    private void viewTransactionHistory() {
        System.out.println("\\n=== Transaction History ===");
        
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        
        String memberId = readStringInput("Enter Member ID (or press Enter for all): ");
        
        List<SimpleTransaction> filteredTransactions = new ArrayList<>();
        for (SimpleTransaction transaction : transactions) {
            if (memberId.trim().isEmpty() || transaction.getMemberId().equals(memberId)) {
                filteredTransactions.add(transaction);
            }
        }
        
        if (filteredTransactions.isEmpty()) {
            System.out.println("No transactions found for the specified criteria.");
            return;
        }
        
        System.out.println("Found " + filteredTransactions.size() + " transaction(s):");
        displayTransactions(filteredTransactions);
    }

    private void viewOverdueBooks() {
        System.out.println("\\n=== Overdue Books ===");
        
        List<SimpleTransaction> overdueTransactions = new ArrayList<>();
        for (SimpleTransaction transaction : transactions) {
            if (transaction.isOverdue()) {
                overdueTransactions.add(transaction);
            }
        }
        
        if (overdueTransactions.isEmpty()) {
            System.out.println("No overdue books found. ✓");
            return;
        }
        
        System.out.println("Found " + overdueTransactions.size() + " overdue book(s):");
        displayTransactions(overdueTransactions);
    }

    private void displayTransactions(List<SimpleTransaction> transactionList) {
        System.out.println("┌─────────────┬────────────┬────────────────┬──────────┬─────────────┬─────────────┬─────────────┐");
        System.out.println("│ Transaction │ Member ID  │ Book ISBN      │ Type     │ Date        │ Due/Return  │ Status      │");
        System.out.println("├─────────────┼────────────┼────────────────┼──────────┼─────────────┼─────────────┼─────────────┤");
        
        for (SimpleTransaction transaction : transactionList) {
            String dateInfo = transaction.getReturnDate() != null ? 
                            transaction.getReturnDate().toString() : 
                            (transaction.getDueDate() != null ? transaction.getDueDate().toString() : "N/A");
            
            System.out.printf("│ %-11s │ %-10s │ %-14s │ %-8s │ %-11s │ %-11s │ %-11s │%n",
                truncate(transaction.getTransactionId(), 11),
                transaction.getMemberId(),
                truncate(transaction.getBookIsbn(), 14),
                truncate(transaction.getType().getDisplayName(), 8),
                transaction.getTransactionDate().toLocalDate().toString(),
                dateInfo,
                truncate(transaction.getStatus().getDisplayName(), 11)
            );
        }
        System.out.println("└─────────────┴────────────┴────────────────┴──────────┴─────────────┴─────────────┴─────────────┘");
    }

    // =================== UTILITY METHODS ===================
    
    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private Integer readOptionalIntegerInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() <= length ? str : str.substring(0, length - 3) + "...";
    }
}