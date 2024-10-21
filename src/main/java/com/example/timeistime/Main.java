package com.example.timeistime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        // Scene oluştur
        Scene scene = new Scene(anchorPane, 1280, 720);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("loginScreenStyle.css")).toExternalForm());

        // Başlık ve sahneyi göster
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
