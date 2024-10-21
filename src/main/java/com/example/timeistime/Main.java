package com.example.timeistime;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginScreen.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        // Scene oluştur
        Scene scene = new Scene(anchorPane, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("loginScreenStyle.css")).toExternalForm());

        GridPane gridPane = (GridPane) anchorPane.lookup(".gridPane");

        // Uygulama açıldığında kenar boşluklarını ayarla
        setPadding(gridPane, scene.getWidth(), scene.getHeight());

        // Yüzdesel kenar boşlukları ayarlamak için dinleyiciler ekleyin
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            setPadding(gridPane, newVal.doubleValue(), scene.getHeight());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            setPadding(gridPane, scene.getWidth(), newVal.doubleValue());
        });

        // Başlık ve sahneyi göster
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void setPadding(GridPane gridPane, double width, double height) {
        double padding = width * 0.1; // %10 padding for left and right
        AnchorPane.setLeftAnchor(gridPane, padding);
        AnchorPane.setRightAnchor(gridPane, padding);

        padding = height * 0.1; // %10 padding for top and bottom
        AnchorPane.setTopAnchor(gridPane, padding);
        AnchorPane.setBottomAnchor(gridPane, padding);
    }

    public static void main(String[] args) {
        launch();
    }
}
