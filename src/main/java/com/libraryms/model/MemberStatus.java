package com.libraryms.model;

public enum MemberStatus {
    ACTIVE("Active", "Member is active and can use all library services"),
    SUSPENDED("Suspended", "Member is temporarily suspended from borrowing"),
    EXPIRED("Expired", "Membership has expired and needs renewal"),
    BLOCKED("Blocked", "Member is blocked due to policy violations"),
    INACTIVE("Inactive", "Member account is inactive but can be reactivated");
    
    private final String displayName;
    private final String description;
    
    MemberStatus(String displayName, String description) {
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
    
    public boolean canBorrowBooks() {
        return this == ACTIVE;
    }
    
    public boolean canRenewMembership() {
        return this == EXPIRED || this == INACTIVE;
    }

}

