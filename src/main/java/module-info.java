module com.example.bustas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.github.librepdf.openpdf;

    // Open the controller package to JavaFX
    opens com.example.bustas.controller to javafx.fxml;
    opens com.example.bustas.view to javafx.fxml;

    exports com.example.bustas;


}