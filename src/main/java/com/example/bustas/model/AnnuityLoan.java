package com.example.bustas.model;

import java.time.LocalDate;
import java.util.List;

public class AnnuityLoan extends Loan { // Handles annuity calcs

    // Monthly Payment = (P * r) / (1 - (1 + r)^-n)
    // P = principal, r = monthly interest rate, n = term (months)

    public AnnuityLoan(double principal, double rate, LocalDate start, int termMonths) {
        super(principal, rate, start, termMonths);
    }

    @Override
    public List<PaymentEntry> calculatePayments() {
        double monthlyRate = super.getAnnualInterestRate() / 12;
        double monthlyPayment = (super.getPrincipal() * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -super.getTermMonths()));

        return null;
    }


}
