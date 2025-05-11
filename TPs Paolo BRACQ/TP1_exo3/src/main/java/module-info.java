module com.example.exo3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.exo3 to javafx.fxml;
    exports com.example.exo3;
}