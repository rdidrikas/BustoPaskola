package com.example.bustas.controller;

import com.example.bustas.model.PaymentEntry;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.util.converter.NumberStringConverter;

public class MainController {

    @FXML private Button calculateAnnuity;
    @FXML private Button calculateLinear;

    @FXML private Slider loanAmountSlider;
    @FXML private TextField loanAmountField;

    @FXML private Slider rateAmountSlider;
    @FXML private TextField interestRateField;

    @FXML private Slider termAmountSlider;
    @FXML private TextField termField;

    @FXML
    private void initialize() {
        // Set up event handlers
        calculateLinear.setOnAction(event -> calculatePayments());
        calculateAnnuity.setOnAction(event -> calculatePayments());

        // Set up bindings

        // Loan amount slider and text field

        loanAmountField.setText(String.format("€%.0f", loanAmountSlider.getValue())); // For first value

        loanAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue() / 1000) * 1000;
            loanAmountSlider.setValue(value); // Round to nearest 1000 euro
            loanAmountField.setText(String.format("€%.0f", value));

        });


        // Interest rate slider and text field

        interestRateField.setText(String.format("%.0f%%", rateAmountSlider.getValue())); // For first value

        rateAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue());
            rateAmountSlider.setValue(value); // Round to nearest %
            interestRateField.setText(String.format("%.0f%%", value));

        });


        // Term slider and text field

        termField.setText(String.format("%.0f month(s)", termAmountSlider.getValue())); // For first value

        termAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue());
            termAmountSlider.setValue(value); // Round to nearest month
            termField.setText(String.format("%.0f month(s)", value));

        });



    }


    private void calculatePayments() {
        // Logic to calculate payments
        System.out.println("Calculating payments...");
    }

    private void exportReport() {
        // Logic to export the payment schedule
        System.out.println("Exporting report...");
    }

    private void applyDeferral() {
        // Logic to apply deferral
        System.out.println("Applying deferral...");
    }
}
