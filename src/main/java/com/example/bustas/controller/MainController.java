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

    @FXML private Button calculateButton;
    @FXML private Button exportButton;
    @FXML private Button deferralButton;

    @FXML private Slider loanAmountSlider;
    @FXML private TextField loanAmountField;

    @FXML private Slider rateAmountSlider;
    @FXML private TextField interestRateField;

    @FXML private Slider termAmountSlider;
    @FXML private TextField termField;

    @FXML
    private void initialize() {
        // Set up event handlers
        calculateButton.setOnAction(event -> calculatePayments());
        exportButton.setOnAction(event -> exportReport());
        deferralButton.setOnAction(event -> applyDeferral());

        // Set up bindings

        // Loan amount slider and text field
        loanAmountField.textProperty().bindBidirectional(loanAmountSlider.valueProperty(), new NumberStringConverter());
        loanAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            loanAmountSlider.setValue(Math.round(newVal.doubleValue() / 1000) * 1000); // Round to nearest 1000 euro
        });


        // Interest rate slider and text field
        interestRateField.textProperty().bindBidirectional(rateAmountSlider.valueProperty(), new NumberStringConverter());
        rateAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            rateAmountSlider.setValue(Math.round(newVal.doubleValue())); // Round to nearest 1%
        });


        // Term slider and text field
        termField.textProperty().bindBidirectional(termAmountSlider.valueProperty(), new NumberStringConverter());
        termAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            termAmountSlider.setValue(Math.round(newVal.doubleValue())); // Round to nearest year
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
