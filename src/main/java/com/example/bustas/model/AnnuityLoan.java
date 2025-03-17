package com.example.bustas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnuityLoan extends Loan { // Handles annuity calcs

    // Monthly Payment = (P * r) / (1 - (1 + r)^-n)
    // P = principal, r = monthly interest rate, n = term (months)

    public AnnuityLoan(double principal, double rate, LocalDate start, int termMonths) {
        super(principal, rate, start, termMonths);
    }

    @Override
    public List<PaymentEntry> calculatePayments() {
        List<PaymentEntry> payments = new ArrayList<>();
        double monthlyRate = (super.getAnnualInterestRate() / 100) / 12; // Convert % to decimal
        double monthlyPayment = (super.getPrincipal() * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -super.getTermMonths()));

        // Round monthly payment to 2 decimals
        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        double remainingBalance = super.getPrincipal();

        for (int month = 1; month <= super.getTermMonths(); month++) {
            double interest = remainingBalance * monthlyRate;
            interest = Math.round(interest * 100.0) / 100.0; // Round interest

            double principal = monthlyPayment - interest;
            principal = Math.round(principal * 100.0) / 100.0; // Round principal

            remainingBalance -= principal;
            remainingBalance = Math.round(remainingBalance * 100.0) / 100.0; // Round balance

            // Handle last payment
            if (month == super.getTermMonths()) {
                principal += remainingBalance;
                principal = Math.round(principal * 100.0) / 100.0;
                remainingBalance = 0;
            }

            payments.add(new PaymentEntry(
                    month,
                    super.getStartDate().plusMonths(month),
                    principal,
                    interest,
                    Math.max(remainingBalance, 0)
            ));
        }
        return payments;
    }
}
