package com.libraryms.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Simplified Book model without external dependencies.
 * Contains all book-related information for the library management system.
 */
public class SimpleBook {
    
    private String isbn;
    private String title;
    private String author;
    private BookCategory category;
    private String publisher;
    private Integer yearPublished;
    private String description;
    private Integer totalCopies;
    private Integer availableCopies;
    private BookStatus status;
    private LocalDateTime dateAdded;
    private LocalDateTime lastUpdated;
    
    // Constructors
    public SimpleBook() {
        this.dateAdded = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.status = BookStatus.AVAILABLE;
        this.totalCopies = 1;
        this.availableCopies = 1;
    }
    
    public SimpleBook(String isbn, String title, String author, BookCategory category) {
        this();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
    }
    
    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { 
        this.title = title; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { 
        this.author = author; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public BookCategory getCategory() { return category; }
    public void setCategory(BookCategory category) { 
        this.category = category; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { 
        this.publisher = publisher; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Integer getYearPublished() { return yearPublished; }
    public void setYearPublished(Integer yearPublished) { 
        this.yearPublished = yearPublished; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer totalCopies) { 
        this.totalCopies = totalCopies; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { 
        this.availableCopies = availableCopies; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public BookStatus getStatus() { return status; }
    public void setStatus(BookStatus status) { 
        this.status = status; 
        this.lastUpdated = LocalDateTime.now();
    }
    
    public LocalDateTime getDateAdded() { return dateAdded; }
    public void setDateAdded(LocalDateTime dateAdded) { this.dateAdded = dateAdded; }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    // Business methods
    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE && availableCopies > 0;
    }
    
    public void borrowCopy() {
        if (isAvailable()) {
            this.availableCopies--;
            if (this.availableCopies == 0) {
                this.status = BookStatus.BORROWED;
            }
            this.lastUpdated = LocalDateTime.now();
        }
    }
    
    public void returnCopy() {
        if (this.availableCopies < this.totalCopies) {
            this.availableCopies++;
            this.status = BookStatus.AVAILABLE;
            this.lastUpdated = LocalDateTime.now();
        }
    }
    
    // Utility methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleBook book = (SimpleBook) o;
        return Objects.equals(isbn, book.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
    @Override
    public String toString() {
        return String.format("Book{isbn='%s', title='%s', author='%s', category=%s, available=%d/%d}", 
                           isbn, title, author, category, availableCopies, totalCopies);
    }
}