package com.example.bustas;


import com.example.bustas.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Application.launch;

public class BustasApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bustas/view/MainWindow.fxml"));
        Parent root = loader.load(); // Loads the FXML and creates the controller

        Scene scene = new Scene(root);

        String css = this.getClass().getResource("/com/example/bustas/view/styles.css").toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setScene(scene);

        MainController mainController = loader.getController();
        mainController.setPrimaryStage(primaryStage);

        primaryStage.setTitle("Busto Paskola");
        primaryStage.show();
    }
}