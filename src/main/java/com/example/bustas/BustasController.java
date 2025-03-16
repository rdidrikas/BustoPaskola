package com.example.bustas;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BustasController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}