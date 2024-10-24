package com.example.timeistime;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    private String token;

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


        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Empty Areas!", "Enter the e-mail and password!", Alert.AlertType.WARNING);
            return;
        }


        try (Connection connection = Database.openDataBase()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = createPreparedStatement(connection, email, password);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet != null && resultSet.next()) {

                    token = CreateJWT.generateToken(email);
                    CreateJWT.checkLocalToken(token);
                    showAlert("Login Succesfull", "Welcome", Alert.AlertType.INFORMATION);
                    System.out.println("Login Succesfull");
                } else {
                    showAlert("Login Unsuccesfull", "E-mail or password incorrect!", Alert.AlertType.ERROR);
                    System.out.println("Login Unsuccesfull");
                }

            }
        } catch (SQLException e) {
            showAlert("Database Error", "An error: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println("Database Error: " + e.getMessage());
        }
    }


    public static PreparedStatement createPreparedStatement(Connection connection, String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }


    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void goRegisterScreen(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registerScreen.fxml"));
            Parent registerRoot = fxmlLoader.load();

            Stage stage = (Stage) emailField.getScene().getWindow();
            Scene scene = new Scene(registerRoot,1280,720);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Register Screen Loading Error: " + e.getMessage());
        }

    }
}
