package com.libraryms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Simplified Transaction class for the Library Management System.
 */
public class SimpleTransaction {
    private String transactionId;
    private String memberId;
    private String bookIsbn;
    private TransactionType type;
    private LocalDateTime transactionDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fineAmount;
    private TransactionStatus status;
    private String notes;

    // Constructor for borrowing
    public SimpleTransaction(String transactionId, String memberId, String bookIsbn, TransactionType type) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookIsbn = bookIsbn;
        this.type = type;
        this.transactionDate = LocalDateTime.now();
        this.status = TransactionStatus.ACTIVE;
        this.fineAmount = 0.0;
        
        if (type == TransactionType.BORROW) {
            this.dueDate = LocalDate.now().plusWeeks(2); // 2 weeks borrowing period
        }
    }

    // Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getBookIsbn() { return bookIsbn; }
    public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }

    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // Helper methods
    public boolean isOverdue() {
        return type == TransactionType.BORROW && 
               status == TransactionStatus.ACTIVE && 
               dueDate != null && 
               LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

    public void calculateFine() {
        if (isOverdue()) {
            long daysOverdue = getDaysOverdue();
            fineAmount = daysOverdue * 0.50; // $0.50 per day overdue
        }
    }

    public void completeReturn() {
        this.returnDate = LocalDate.now();
        this.status = TransactionStatus.COMPLETED;
        calculateFine();
    }

    @Override
    public String toString() {
        return String.format("Transaction{id='%s', member='%s', book='%s', type=%s, date=%s, due=%s, status=%s, fine=$%.2f}",
                transactionId, memberId, bookIsbn, type.getDisplayName(),
                transactionDate.toLocalDate(), dueDate, status.getDisplayName(), fineAmount);
    }
}