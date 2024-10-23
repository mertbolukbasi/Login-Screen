package com.example.timeistime;

import javafx.application.Application;
import javafx.stage.Stage;


import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        PrimaryStage.setStage1(stage);
        PrimaryStage.loginScreen();

    }



    public static void main(String[] args) {
        launch();
    }
}
