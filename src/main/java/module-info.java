module com.example.timeistime {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.timeistime to javafx.fxml;
    exports com.example.timeistime;
}