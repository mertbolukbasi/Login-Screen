package com.example.timeistime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class PrimaryStage {

    private static Stage stage1;


    public static void loginScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PrimaryStage.class.getResource("loginScreen.fxml"));
        StackPane stackPane = fxmlLoader.load();

        Scene scene = new Scene(stackPane, 1280, 720);
        stage1.setScene(scene);
        stage1.setTitle("Time is Time");
        stage1.setResizable(true);
        stage1.show();
    }


    public static Stage getStage1() {
        return stage1;
    }

    public static void setStage1(Stage stage1) {
        PrimaryStage.stage1 = stage1;
    }
}
