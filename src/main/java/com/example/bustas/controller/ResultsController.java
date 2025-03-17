package com.example.bustas.controller;

import com.example.bustas.model.Loan;
import com.example.bustas.model.PaymentEntry;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.time.LocalDate;
import java.util.List;

public class ResultsController {

    @FXML private LineChart<Number, Number> paymentChart;
    @FXML private TableView<PaymentEntry> paymentTable;
    @FXML private TableColumn<PaymentEntry, Integer> monthColumn;
    @FXML private TableColumn<PaymentEntry, LocalDate> dateColumn;
    @FXML private TableColumn<PaymentEntry, Double> principalColumn;
    @FXML private TableColumn<PaymentEntry, Double> interestColumn;
    @FXML private TableColumn<PaymentEntry, Double> balanceColumn;


    private Stage stage;
    private Scene previousScene;

    public void initializeData(Stage stage, Scene previousScene, List<PaymentEntry> payments) {
        this.stage = stage;
        this.previousScene = previousScene;

        // Init table
        monthColumn.setCellValueFactory(cellData -> cellData.getValue().monthProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().paymentDateProperty());
        principalColumn.setCellValueFactory(cellData -> cellData.getValue().principalProperty().asObject());
        interestColumn.setCellValueFactory(cellData -> cellData.getValue().interestProperty().asObject());
        balanceColumn.setCellValueFactory(cellData -> cellData.getValue().remainingBalanceProperty().asObject());

        // Format collumns
        principalColumn.setCellFactory(col -> new TableCell<PaymentEntry, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText("");
                } else {
                    setText(String.format("€%.2f", value));
                }
            }
        });

        interestColumn.setCellFactory(col -> new TableCell<PaymentEntry, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText("");
                } else {
                    setText(String.format("€%.2f", value));
                }
            }
        });

        balanceColumn.setCellFactory(col -> new TableCell<PaymentEntry, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText("");
                } else {
                    setText(String.format("€%.2f", value));
                }
            }
        });

        // Update table
        paymentTable.getItems().setAll(payments);


        // Init chart
        initializeChart(payments);

    }

    private void initializeChart(List<PaymentEntry> payments) {
        XYChart.Series<Number, Number> principalSeries = new XYChart.Series<>();
        XYChart.Series<Number, Number> interestSeries = new XYChart.Series<>();

        principalSeries.setName("Principal");
        interestSeries.setName("Interest");

        for (PaymentEntry payment : payments) {
            principalSeries.getData().add(new XYChart.Data<>(payment.getMonth(), payment.getPrincipal()));
            interestSeries.getData().add(new XYChart.Data<>(payment.getMonth(), payment.getInterest()));
        }

        //paymentChart.getData().clear();
        //paymentChart.getData().addAll(principalSeries, interestSeries);

    }

    @FXML
    private void handleBackButton() {
        this.stage.setScene(previousScene); // Return to the main window
    }

}
