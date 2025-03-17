package com.example.bustas.controller;

import com.example.bustas.model.PaymentEntry;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.List;

public class ResultsController {


    private Stage stage;
    private Scene previousScene;

    public void initializeData(Stage stage, Scene previousScene) {
        this.stage = stage;
        this.previousScene = previousScene;
    }


    @FXML
    private void handleBackButton() {
        this.stage.setScene(previousScene); // Return to the main window
    }
}
