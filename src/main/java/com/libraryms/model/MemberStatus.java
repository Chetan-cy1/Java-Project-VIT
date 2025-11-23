package com.libraryms.model;

/**
 * Enumeration representing the current status of a library member.
 * Used to control member access and borrowing privileges.
 */
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
    
    /**
     * Check if this status allows borrowing books
     */
    public boolean canBorrowBooks() {
        return this == ACTIVE;
    }
    
    /**
     * Check if this status allows renewing membership
     */
    public boolean canRenewMembership() {
        return this == EXPIRED || this == INACTIVE;
    }
}