module com.example.timeistime {
    requires org.kordamp.bootstrapfx.core;
    requires com.jfoenix;
    requires java.sql;
    requires jjwt.api;
    requires MaterialFX;


    opens com.example.timeistime to javafx.fxml;
    exports com.example.timeistime;
}