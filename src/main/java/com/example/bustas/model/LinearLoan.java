package com.example.bustas.model;

import java.time.LocalDate;
import java.util.List;

public class LinearLoan extends Loan { // Handles linear calcs

    public LinearLoan(double principal, double rate, LocalDate start, int termMonths) {
        super(principal, rate, start, termMonths);
    }

    @Override
    public List<PaymentEntry> calculatePayments() {
        return null;
    }


}
