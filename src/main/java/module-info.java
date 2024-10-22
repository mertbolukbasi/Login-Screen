module com.example.timeistime {
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires javafx.controls;
    requires java.sql;


    opens com.example.timeistime to javafx.fxml;
    exports com.example.timeistime;
}