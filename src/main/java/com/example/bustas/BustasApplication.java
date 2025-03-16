package com.example.bustas;

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

        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getResource("/com/example/bustas/view/MainWindow.fxml")
        ));
        // Group root = new Group();
        Scene scene = new Scene(root, 1080, 720, Color.color(0.2, 0.2, 0.2));
        //scene.getStylesheets().add("src/main/resources/com/example/bustas/view/styles.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("Busto Paskola");
        primaryStage.show();
    }
}