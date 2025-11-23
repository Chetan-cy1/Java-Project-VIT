package com.libraryms.model;

public enum TransactionStatus {
    PENDING("Pending", "Transaction is pending processing"),
    ACTIVE("Active", "Transaction is active (book currently borrowed)"),
    RETURNED("Returned", "Book has been returned successfully"),
    OVERDUE("Overdue", "Book return is overdue"),
    CANCELLED("Cancelled", "Transaction was cancelled"),
    COMPLETED("Completed", "Transaction completed successfully"),
    FAILED("Failed", "Transaction failed to process");
    
    private final String displayName;
    private final String description;
    
    TransactionStatus(String displayName, String description) {
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
    
    public boolean isActiveBorrowing() {
        return this == ACTIVE || this == OVERDUE;
    }
    
    public boolean isCompleted() {
        return this == RETURNED || this == COMPLETED || this == CANCELLED;
    }

}
