package com.example.timeistime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        Scene scene = new Scene(anchorPane, 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("loginScreenStyle.css")).toExternalForm());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Database database = new Database();
        database.openDataBase();


        database.closeDataBase();
    }



    public static void main(String[] args) {
        launch();
    }
}
