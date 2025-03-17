package com.example.bustas.controller;

import com.example.bustas.model.AnnuityLoan;
import com.example.bustas.model.LinearLoan;
import com.example.bustas.model.Loan;
import com.example.bustas.model.PaymentEntry;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class MainController {

    private Stage primaryStage;
    private Scene mainScene;

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

        // Loan amount slider and text field

        loanAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue() / 1000) * 1000;
            loanAmountSlider.setValue(value); // Round to nearest 1000 euro
            loanAmountField.setText(String.format("%.0f €", value));

        });

        loanAmountField.textProperty().bindBidirectional(loanAmountSlider.valueProperty(), new NumberStringConverter());

        loanAmountField.setText(String.format("%.0f €", loanAmountSlider.getValue())); // For first value

        // Interest rate slider and text field

        rateAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue() / 0.25f) * 0.25f;
            rateAmountSlider.setValue(value); // Round to nearest %
            interestRateField.setText(String.format("%.2f%%", value));

        });

        interestRateField.textProperty().bindBidirectional(rateAmountSlider.valueProperty(), new NumberStringConverter());

        interestRateField.setText(String.format("%.0f%%", rateAmountSlider.getValue())); // For first value

        // Term slider and text field

        termAmountSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double value = Math.round(newVal.doubleValue());
            termAmountSlider.setValue(value); // Round to nearest month
            termField.setText(String.format("%.0f month(s)", value));

        });

        termField.textProperty().bindBidirectional(termAmountSlider.valueProperty(), new NumberStringConverter());

        termField.setText(String.format("%.0f month(s)", termAmountSlider.getValue())); // For first value



    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
        this.mainScene = primaryStage.getScene();
    }


    private void calculatePayments(String type, ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bustas/view/GraphWindow.fxml"));
        Parent root = loader.load(); // Loads the FXML and creates the controller
        this.primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene graphScene = new Scene(root);
        this.primaryStage.setScene(graphScene);
        this.primaryStage.show();

        Loan loan = null;

        if(type == "linear") {
            this.primaryStage.setTitle("Linear Loan Payment Schedule");
            loan = new LinearLoan(loanAmountSlider.getValue(), rateAmountSlider.getValue(), LocalDate.now(), (int) termAmountSlider.getValue());
        } else {
            this.primaryStage.setTitle("Annuity Loan Payment Schedule");
            loan = new AnnuityLoan(loanAmountSlider.getValue(), rateAmountSlider.getValue(), LocalDate.now(), (int) termAmountSlider.getValue());
        }

        List<PaymentEntry> payments = loan.calculatePayments();

        ResultsController resultsController = loader.getController();
        resultsController.initializeData(this.primaryStage, mainScene, payments);

    }

    private void exportReport() {
        // Logic to export the payment schedule
        System.out.println("Exporting report...");
    }

    private void applyDeferral() {
        // Logic to apply deferral
        System.out.println("Applying deferral...");
    }


    public void setLinear(ActionEvent event) throws IOException {
        calculatePayments("linear", event);
    }

    public void setAnnuity(ActionEvent event) throws IOException {
        calculatePayments("annuity", event);
    }


}
