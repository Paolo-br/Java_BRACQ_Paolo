module com.example.tp1_exo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp1_exo1 to javafx.fxml;
    exports com.example.tp1_exo1;
}