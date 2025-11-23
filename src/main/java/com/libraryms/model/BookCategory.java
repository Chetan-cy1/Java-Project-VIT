package com.libraryms.model;

/**
 * Enumeration representing different book categories in the library.
 * Used for organizing and filtering books by subject area.
 */
public enum BookCategory {
    FICTION("Fiction", "Literary works of imagination"),
    NON_FICTION("Non-Fiction", "Factual and informational works"),
    SCIENCE_TECHNOLOGY("Science & Technology", "Scientific and technical publications"),
    HISTORY_POLITICS("History & Politics", "Historical and political works"),
    BUSINESS_ECONOMICS("Business & Economics", "Business and economic literature"),
    HEALTH_MEDICINE("Health & Medicine", "Medical and health-related publications"),
    ARTS_LITERATURE("Arts & Literature", "Artistic and literary works"),
    EDUCATION_REFERENCE("Education & Reference", "Educational materials and reference books"),
    CHILDREN_YOUNG_ADULT("Children & Young Adult", "Books for younger readers"),
    RELIGION_PHILOSOPHY("Religion & Philosophy", "Religious and philosophical texts"),
    TRAVEL_GEOGRAPHY("Travel & Geography", "Travel guides and geographical works"),
    BIOGRAPHY_AUTOBIOGRAPHY("Biography & Autobiography", "Life stories and memoirs"),
    COMPUTER_PROGRAMMING("Computer & Programming", "Technology and programming books"),
    OTHER("Other", "Miscellaneous categories");
    
    private final String displayName;
    private final String description;
    
    BookCategory(String displayName, String description) {
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
     * Get category by display name (case-insensitive)
     */
    public static BookCategory fromDisplayName(String displayName) {
        for (BookCategory category : values()) {
            if (category.displayName.equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        return OTHER;
    }
}