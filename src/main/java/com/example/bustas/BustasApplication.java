package com.example.bustas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class BustasApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, Color.color(0.2, 0.2, 0.2));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Busto Paskola");
        primaryStage.show();
    }
}