package com.example.bustas.controller;

import com.example.bustas.model.PaymentEntry;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

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

        NumberAxis xAxis = (NumberAxis) paymentChart.getXAxis();
        NumberAxis yAxis = (NumberAxis) paymentChart.getYAxis();

        xAxis.setLabel("Month");
        yAxis.setLabel("Amount (€)");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, "€", null));

        XYChart.Series<Number, Number> principalSeries = new XYChart.Series<>();
        principalSeries.setName("Principal");
        XYChart.Series<Number, Number> interestSeries = new XYChart.Series<>();
        interestSeries.setName("Interest");

        for (PaymentEntry payment : payments) {
            principalSeries.getData().add(new XYChart.Data<>(payment.getMonth(), payment.getPrincipal()));
            interestSeries.getData().add(new XYChart.Data<>(payment.getMonth(), payment.getInterest()));
        }

        paymentChart.getData().clear();
        paymentChart.getData().addAll(principalSeries, interestSeries);

    }

    @FXML
    private void handleBackButton() {
        this.stage.setScene(previousScene); // Return to the main window
    }

    @FXML
    private void exportPDF() {

        String fileName = handleExport();
        createPdfTable(paymentTable);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName + ".pdf"));
            document.open();
            document.add(new Paragraph("Loan Payment Schedule\n\n"));
            document.add(createPdfTable(paymentTable));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private PdfPTable createPdfTable(TableView<?> table) throws DocumentException {

        int numColumns = table.getColumns().size();
        PdfPTable pdfTable = new PdfPTable(numColumns);

        for (int i = 0; i < numColumns; i++) {
            pdfTable.addCell(table.getColumns().get(i).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            for (int j = 0; j < numColumns; j++) {
                Object value = table.getColumns().get(j).getCellData(i);
                pdfTable.addCell(value.toString());
            }
        }

        return pdfTable;
    }

    private String handleExport(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Export to PDF");
        dialog.setHeaderText(null);
        dialog.setContentText("File name:");

        String css = getClass().getResource("/com/example/bustas/view/styles.css").toExternalForm();
        dialog.getDialogPane().getStylesheets().add(css);

        dialog.showAndWait();

        return dialog.getResult();
    }

    @FXML
    private void postpone() {

        // Month delay pop up
        TextInputDialog postponeDialog = new TextInputDialog();
        postponeDialog.setTitle("Postpone Payments");
        postponeDialog.setHeaderText(null);
        postponeDialog.setContentText("Enter number of months to postpone:");

        String css = getClass().getResource("/com/example/bustas/view/styles.css").toExternalForm();
        postponeDialog.getDialogPane().getStylesheets().add(css);
        postponeDialog.showAndWait();
        String postpone = postponeDialog.getResult();

        if (postpone == null) return;

        int postponeMonths = 0;
        try {
            postponeMonths = Integer.parseInt(postpone);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number for postpone months.");
            postpone();
        }

        // Interest Rate pop up
        TextInputDialog interestDialog = new TextInputDialog();
        interestDialog.setTitle("Postpone Payments");
        interestDialog.setHeaderText(null);
        interestDialog.setContentText("Enter additional (yearly) interest rate (in %):");
        interestDialog.getDialogPane().getStylesheets().add(css);
        interestDialog.showAndWait();

        String interestResult = interestDialog.getResult();

        if (interestResult == null) return;

        double postponeRate = 0;
        try {
            postponeRate = Double.parseDouble(interestResult) / 100.0;
        } catch (NumberFormatException e) {
            System.err.println("Invalid interest rate.");
            postpone();
        }

        // Get the current payment schedule from the table
        List<PaymentEntry> originalPayments = paymentTable.getItems();
        if (originalPayments.isEmpty()) return;

        PaymentEntry firstPayment = originalPayments.get(0); // First payment date needed
        double originalLoanAmount = firstPayment.getPrincipal() + firstPayment.getRemainingBalance();

        List<PaymentEntry> newPayments = new ArrayList<>(); // New payments

        for (int i = 1; i <= postponeMonths; i++) {
            PaymentEntry postponeEntry = new PaymentEntry(i, firstPayment.getPaymentDate().plusMonths(i - 1), 0.0,
                    originalLoanAmount * (postponeRate / 12), originalLoanAmount);
            postponeEntry.setMonth(i);
            newPayments.add(postponeEntry);
        }

        // Shift each payment's month and payment date forward by the postpone period and add to new list
        for (PaymentEntry payment : originalPayments) {
            payment.setMonth(payment.getMonth() + postponeMonths);
            payment.setPaymentDate(payment.getPaymentDate().plusMonths(postponeMonths));
            newPayments.add(payment); // Adding to the new list
        }

        // Refresh the table and chart
        paymentTable.getItems().setAll(newPayments);
        paymentTable.refresh();
        initializeChart(newPayments);
    }

}
