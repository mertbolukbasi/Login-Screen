package com.example.timeistime;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainScreen {

    private static Stage stage1;

   public static void getMainScreen() {

      try {
          FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("mainScreen.fxml"));
          Parent root = fxmlLoader.load();

          Scene scene = new Scene(root,1280,720);
          stage1.setScene(scene);
          stage1.setTitle("Main Screen");
          stage1.setResizable(true);
          stage1.setMaximized(true);
          stage1.show();

      }

      catch (IOException e) {
          System.out.println("Main Load Error: " + e.getMessage());
      }
   }


    public static void setStage1(Stage stage1) {
        MainScreen.stage1 = stage1;
    }
}
