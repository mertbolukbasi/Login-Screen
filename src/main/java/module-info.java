module com.example.timeistime {
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.timeistime to javafx.fxml;
    exports com.example.timeistime;
}