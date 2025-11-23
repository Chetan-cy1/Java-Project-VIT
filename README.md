# Digital Library Management System

## Overview
This project is a simple Library Management System written in Java.
It runs completely in the console and handles basic operations like managing books, maintaining member records, and keeping track of book borrowing and returns. The aim of the project was to create a small working system that shows how a real library might organise its data and day-to-day tasks.

## Features
### Core Modules
- **Book Catalog**: Simple CRUD (Create, Read, Update, Delete) operations for books. Each book is tracked by its ISBN.
- **Member Registration**: Allows librarians to register new members, update their contact information, and retrieve member details.
- **Transaction Processing**: Manages the entire borrowing lifecycle, from loaning a book to processing its return, including automatic fine calculations.

### Key Capabilities
- Menu-Driven Interface: The application runs entirely within the console using clear text menus.
- Real-time Stock Check: Tracks the current number of available copies for every book.
- Member borrowing limits based on membership type
- Borrowing Controls: Enforces member-specific borrowing limits based on their membership type (e.g., Student, Faculty).
- Automated Fine System: Calculates and applies fines immediately when a book is returned overdue.
- History Tracking: Maintains a record of all borrowing and return events.
- Search Filters: Quick search functionality for locating specific books or members.
- Input Handling: Includes basic checks to ensure user input is valid before processing.

## Technologies Used
- **Language**: Java (tested with JAVA 22)
- **Build Tool**: Apache Maven 3.11.0
- **Dependencies**: 
  - Jackson (JSON processing)
  - Logback (Logging framework)
  - JUnit 5 (Testing framework)
  - Hibernate Validator (Data validation)

## Project Structure
```
java_project/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── libraryms/
│                   ├── LibraryManagementApplication.java  # Main application class
│                   └── model/                             # Data model classes
│                       ├── BookCategory.java              # Book category enumeration
│                       ├── BookStatus.java                # Book status enumeration
│                       ├── MemberStatus.java              # Member status enumeration
│                       ├── MemberType.java                # Member type enumeration
│                       ├── SimpleBook.java                # Book entity class
│                       ├── SimpleMember.java              # Member entity class
│                       ├── SimpleTransaction.java         # Transaction entity class
│                       ├── TransactionStatus.java         # Transaction status enumeration
│                       └── TransactionType.java           # Transaction type enumeration
├── com/                                           # Compiled classes output
│   └── libraryms/
└── README.md                                      # Project documentation
```

## Installation & Setup

### Prerequisites
- **Java installed (preferably Java 22)** 

### Steps to Run
1. **Navigate to project directory:**
   ```powershell
   cd C:\Coding\java_project
   ```

2. **Compile all Java files:**
   ```powershell
   javac -d . src/main/java/com/libraryms/*.java src/main/java/com/libraryms/model/*.java
   ```

3. **Run the application:**
   ```powershell
   java com.libraryms.LibraryManagementApplication
   ```

   Or use this single command to compile and run:
   ```powershell
   javac -d . src/main/java/com/libraryms/*.java src/main/java/com/libraryms/model/*.java; java com.libraryms.LibraryManagementApplication
   ```

### Quick Start (One-time setup)
```powershell
# Navigate to project
cd C:\Coding\java_project

# Compile everything
javac -d . src/main/java/com/libraryms/*.java src/main/java/com/libraryms/model/*.java

# Run the application
java com.libraryms.LibraryManagementApplication
```

## Usage Instructions

### Starting the Application
When the application starts, it shows a simple menu.
From there, you can select:

```
╔════════════════════════════════════════════════════════════════╗
║              Digital Library Management System                 ║
║                     Version 1.0.0                              ║
╚════════════════════════════════════════════════════════════════╝

┌─────────────────── MAIN MENU ───────────────────┐
│  1. Book Management                             │
│  2. Member Management                           │
│  3. Transaction Management                      │
│  0. Exit                                        │
└─────────────────────────────────────────────────┘
```
Each section has options like adding books, searching books, borrowing, returning, etc.

### Sample Workflows

#### Book Management
- **Add New Book**: Enter ISBN, title, author, category, and optional publisher/year
- **Search Books**: Find books by title, author, or ISBN
- **View All Books**: Display complete book inventory
- **Update Book Info**: Modify title, author, or publisher information
- **Delete Book**: Remove books from the system

#### Member Management
- **Register New Member**: Create member accounts with auto-generated IDs
- **Search Members**: Find members by name, ID, or email
- **View Member Details**: See complete member information including borrowing status
- **Update Member Info**: Modify contact information

#### Transaction Management
- **Borrow Book**: Process book loans with automatic due date calculation
- **Return Book**: Handle book returns with fine calculation for overdue items
- **View Transaction History**: Browse borrowing and return records
- **View Overdue Books**: Monitor books past their due dates

### Sample Data
The application comes pre-loaded with sample data:
- **Books**: "Effective Java" and "Clean Code"
- **Members**: Sample student and faculty accounts
- **Member Types**: Student (upto 5 books), Faculty (up to 10 books), Staff (up to 7 books)

## Development Commands

### Build Commands
```powershell
# Compile all Java files
javac -d . src/main/java/com/libraryms/*.java src/main/java/com/libraryms/model/*.java

# Clean compiled files (delete .class files)
Get-ChildItem -Recurse -Filter "*.class" | Remove-Item

# Check what was compiled
Get-ChildItem -Recurse -Filter "*.class"
```

### Running the Application
```powershell
# After compilation, run the main class
java com.libraryms.LibraryManagementApplication
```

## Configuration

### Logging Configuration
The application uses Logback for logging. Configuration is in `src/main/resources/logback.xml`.

### Member Types and Limits
- **Student**: Maximum 5 books, 14-day loan period
- **Faculty**: Maximum 10 books, 30-day loan period  
- **Staff**: Maximum 7 books, 21-day loan period

### Fine Structure
- **Daily fine rate**: $0.50 per day per overdue book
- **Maximum fine threshold**: $50.00 (blocks further borrowing)

## Architecture Notes

### Design Patterns
- **Model Classes**: Simple POJOs with validation
- **Console Interface**: Menu-driven user interaction
- **In-Memory Storage**: ArrayList-based data management
- **Enumeration Types**: Type-safe constants for categories and statuses

### Key Classes
- **LibraryManagementApplication**: Main application controller
- **SimpleBook**: Book entity with status tracking
- **SimpleMember**: Member entity with borrowing limits
- **SimpleTransaction**: Transaction record with fine calculation

---


**Note**: 
- Data is not saved permanently. When you close the program, everything resets because the system uses only in-memory lists.
- This project focuses on the basic working of a library system rather than a full production-ready application.


