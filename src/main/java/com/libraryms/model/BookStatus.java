package com.libraryms.model;

/**
 * Enumeration representing the current status of a book in the library.
 * Used to track book availability and condition.
 */
public enum BookStatus {
    AVAILABLE("Available", "Book is available for borrowing"),
    BORROWED("Borrowed", "Book is currently borrowed by a member"),
    RESERVED("Reserved", "Book is reserved for a specific member"),
    MAINTENANCE("Under Maintenance", "Book is being repaired or maintained"),
    LOST("Lost", "Book has been reported as lost"),
    DAMAGED("Damaged", "Book is damaged and cannot be borrowed"),
    WITHDRAWN("Withdrawn", "Book has been permanently removed from circulation");
    
    private final String displayName;
    private final String description;
    
    BookStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
    
    /**
     * Check if this status allows borrowing
     */
    public boolean canBeBorrowed() {
        return this == AVAILABLE;
    }
    
    /**
     * Check if this status is considered active (not lost/withdrawn)
     */
    public boolean isActive() {
        return this != LOST && this != WITHDRAWN;
    }
}