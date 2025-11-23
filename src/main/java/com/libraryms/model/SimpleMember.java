package com.libraryms.model;

import java.time.LocalDate;

public class SimpleMember {
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private MemberType memberType;
    private MemberStatus status;
    private LocalDate dateOfBirth;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private double totalFinesOwed;
    private int currentBorrowedBooks;

    public SimpleMember(String memberId, String firstName, String lastName, String email, MemberType memberType) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.memberType = memberType;
        this.status = MemberStatus.ACTIVE;
        this.membershipStartDate = LocalDate.now();
        this.membershipEndDate = LocalDate.now().plusMonths(memberType.getMembershipDurationMonths());
        this.totalFinesOwed = 0.0;
        this.currentBorrowedBooks = 0;
    }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public MemberType getMemberType() { return memberType; }
    public void setMemberType(MemberType memberType) { this.memberType = memberType; }

    public MemberStatus getStatus() { return status; }
    public void setStatus(MemberStatus status) { this.status = status; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public LocalDate getMembershipStartDate() { return membershipStartDate; }
    public void setMembershipStartDate(LocalDate membershipStartDate) { this.membershipStartDate = membershipStartDate; }

    public LocalDate getMembershipEndDate() { return membershipEndDate; }
    public void setMembershipEndDate(LocalDate membershipEndDate) { this.membershipEndDate = membershipEndDate; }

    public double getTotalFinesOwed() { return totalFinesOwed; }
    public void setTotalFinesOwed(double totalFinesOwed) { this.totalFinesOwed = totalFinesOwed; }

    public int getCurrentBorrowedBooks() { return currentBorrowedBooks; }
    public void setCurrentBorrowedBooks(int currentBorrowedBooks) { this.currentBorrowedBooks = currentBorrowedBooks; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isActive() {
        return status == MemberStatus.ACTIVE && membershipEndDate.isAfter(LocalDate.now());
    }

    public boolean canBorrowBooks() {
        return isActive() && currentBorrowedBooks < memberType.getMaxBooksAllowed() && totalFinesOwed < 50.0;
    }

    public void addFine(double amount) {
        this.totalFinesOwed += amount;
    }

    public void payFine(double amount) {
        this.totalFinesOwed = Math.max(0, this.totalFinesOwed - amount);
    }

    public void borrowBook() {
        if (canBorrowBooks()) {
            currentBorrowedBooks++;
        } else {
            throw new IllegalStateException("Member cannot borrow more books");
        }
    }

    public void returnBook() {
        if (currentBorrowedBooks > 0) {
            currentBorrowedBooks--;
        }
    }

    public void renewMembership(int months) {
        this.membershipEndDate = this.membershipEndDate.plusMonths(months);
        if (this.status == MemberStatus.EXPIRED) {
            this.status = MemberStatus.ACTIVE;
        }
    }

    @Override
    public String toString() {
        return String.format("Member{id='%s', name='%s', email='%s', type=%s, status=%s, borrowed=%d/%d, fines=$%.2f}",
                memberId, getFullName(), email, memberType.getDisplayName(), status.getDisplayName(),
                currentBorrowedBooks, memberType.getMaxBooksAllowed(), totalFinesOwed);
    }

}
