package com.example.bustas.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LinearLoan extends Loan { // Handles linear calcs

    public LinearLoan(double principal, double rate, LocalDate start, int termMonths) {
        super(principal, rate, start, termMonths);
    }

    @Override
    public List<PaymentEntry> calculatePayments() {
        List<PaymentEntry> payments = new ArrayList<>();
        double monthlyPrincipal = super.getPrincipal() / super.getTermMonths();
        monthlyPrincipal = Math.round(monthlyPrincipal * 100.0) / 100.0; // Round principal

        double monthlyRate = (super.getAnnualInterestRate() / 100) / 12; // Convert % to decimal
        double remainingBalance = super.getPrincipal();

        for (int month = 1; month <= super.getTermMonths(); month++) {
            double interest = remainingBalance * monthlyRate;
            interest = Math.round(interest * 100.0) / 100.0; // Round interest

            remainingBalance -= monthlyPrincipal;
            remainingBalance = Math.round(remainingBalance * 100.0) / 100.0; // Round balance

            payments.add(new PaymentEntry(
                    month,
                    super.getStartDate().plusMonths(month),
                    monthlyPrincipal,
                    interest,
                    Math.max(remainingBalance, 0)
            ));
        }
        return payments;
    }


}
