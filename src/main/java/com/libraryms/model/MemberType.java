package com.libraryms.model;

public enum MemberType {
    STUDENT("Student", "Student membership with basic privileges", 5, 12),
    FACULTY("Faculty", "Faculty membership with extended privileges", 10, 24),
    STAFF("Staff", "Staff membership with standard privileges", 7, 12),
    PUBLIC("Public", "General public membership", 3, 6),
    RESEARCHER("Researcher", "Research membership with specialized access", 15, 12),
    SENIOR_CITIZEN("Senior Citizen", "Senior citizen membership with special benefits", 5, 12);
    
    private final String displayName;
    private final String description;
    private final int maxBooksAllowed;
    private final int membershipDurationMonths;
    
    MemberType(String displayName, String description, int maxBooksAllowed, int membershipDurationMonths) {
        this.displayName = displayName;
        this.description = description;
        this.maxBooksAllowed = maxBooksAllowed;
        this.membershipDurationMonths = membershipDurationMonths;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getMaxBooksAllowed() {
        return maxBooksAllowed;
    }
    
    public int getMembershipDurationMonths() {
        return membershipDurationMonths;
    }
    
    @Override
    public String toString() {
        return displayName;
    }
   
    public static MemberType fromDisplayName(String displayName) {
        for (MemberType type : values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return PUBLIC; 
    }

}

