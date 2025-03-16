module com.example.bustas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bustas to javafx.fxml;
    exports com.example.bustas;
}