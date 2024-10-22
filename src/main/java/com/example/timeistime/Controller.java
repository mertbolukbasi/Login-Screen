package com.example.timeistime;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class Controller {

    @FXML
    private TextField emailField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void getUserLogin(ActionEvent event) {

        String email = emailField.getText();
        String password = passwordField.getText();

        if(email.isEmpty() || password.isEmpty()) {
            System.out.println("Bos");
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = Database.openDataBase();

            String sql = "select * from logindata where Emails=? and Password=?";
            if(connection != null) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Login Successful");
                    a.setContentText("CSS'teki mesajini gordum gotunu sikeyim");
                    a.showAndWait();
                    System.out.println("Basarili");
                }

                else {
                    System.out.println("Basarisiz");
                }
            }


        } catch (SQLException e) {
            System.out.println("DataBase Error: " + e.getMessage());
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
                if(connection != null) Database.closeDataBase();
            }
            catch (SQLException e) {
                System.out.println("DataBase Error: " + e.getMessage());
            }
        }


    }
}