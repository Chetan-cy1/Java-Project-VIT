package com.libraryms.model;

public enum TransactionType {
    BORROW("Borrow", "Member borrows a book"),
    RETURN("Return", "Member returns a borrowed book"),
    RENEW("Renew", "Member renews borrowing period"),
    RESERVE("Reserve", "Member reserves a book"),
    CANCEL_RESERVATION("Cancel Reservation", "Member cancels a book reservation"),
    FINE_PAYMENT("Fine Payment", "Member pays outstanding fines"),
    LOST_BOOK("Lost Book", "Book reported as lost by member"),
    DAMAGED_BOOK("Damaged Book", "Book returned in damaged condition");
    
    private final String displayName;
    private final String description;
    
    TransactionType(String displayName, String description) {
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
    
    public boolean affectsAvailability() {
        return this == BORROW || this == RETURN || this == LOST_BOOK;
    }
    
    public boolean requiresAvailableBook() {
        return this == BORROW || this == RESERVE;
    }

}
