package com.example.bustas.model;

import java.time.LocalDate;
import java.util.List;

abstract class Loan { // Abstract base class
    private double principal;      // Encapsulated (private)
    private double annualInterestRate;
    private LocalDate startDate;
    private int termMonths;

    public Loan(double principal, double rate, LocalDate start, int termMonths) {
        this.principal = principal;  // Using "this"
        this.annualInterestRate = rate;
        this.startDate = start;
        this.termMonths = termMonths;
    }

    public abstract List<PaymentEntry> calculatePayments(); // Abstract method

    // Getters

    public double getPrincipal() {
        return principal;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getTermMonths() {
        return termMonths;
    }


}
