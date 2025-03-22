package com.example.bustas.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class PaymentEntry { // Data class for monthly payment details
    private final SimpleIntegerProperty month;
    private final SimpleObjectProperty<LocalDate> paymentDate;
    private final SimpleDoubleProperty principal;
    private final SimpleDoubleProperty interest;
    private final SimpleDoubleProperty remainingBalance;

    public PaymentEntry(int month, LocalDate paymentDate, double principal, double interest, double remainingBalance) {
        this.month = new SimpleIntegerProperty(month);
        this.paymentDate = new SimpleObjectProperty<>(paymentDate);
        this.principal = new SimpleDoubleProperty(principal);
        this.interest = new SimpleDoubleProperty(interest);
        this.remainingBalance = new SimpleDoubleProperty(remainingBalance);
    }


    // Getters for properties
    public SimpleIntegerProperty monthProperty() {
        return month;
    }

    public SimpleObjectProperty<LocalDate> paymentDateProperty() {
        return paymentDate;
    }

    public SimpleDoubleProperty principalProperty() {
        return principal;
    }

    public SimpleDoubleProperty interestProperty() {
        return interest;
    }

    public SimpleDoubleProperty remainingBalanceProperty() {
        return remainingBalance;
    }

   // Getters for values
    public int getMonth() {
        return month.get();
    }

    public LocalDate getPaymentDate() {
        return paymentDate.get();
    }

    public double getPrincipal() {
        return principal.get();
    }

    public double getInterest() {
        return interest.get();
    }

    public double getRemainingBalance() {
        return remainingBalance.get();
    }

    // Setters for values
    public void setMonth(int month) {
        this.month.set(month);
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate.set(paymentDate);
    }

    public void setPrincipal(double principal) {
        this.principal.set(principal);
    }

    public void setInterest(double interest) {
        this.interest.set(interest);
    }

    public void setRemainingBalance(double remainingBalance) {
        this.remainingBalance.set(remainingBalance);
    }

}
