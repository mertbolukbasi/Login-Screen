package com.example.timeistime;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;

public class RegisterController {

    @FXML
    private DatePicker birthdayField;

    @FXML
    private TextField confirmPassword;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private JFXButton registerButton;

    @FXML
    private TextField usernameField;

    @FXML

    void getUserRegister(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        String username = usernameField.getText();
        Date birthday = Date.valueOf(birthdayField.getValue());


        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            LoginController.showAlert("Empty Areas!", "Enter the username, e-mail, and password!", Alert.AlertType.WARNING);
            return;
        }

        try (Connection connection = Database.openDataBase()) {
            assert connection != null;

            String checkUserQuery = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
                checkUserStatement.setString(1, email);
                try (ResultSet resultSet = checkUserStatement.executeQuery()) {

                    if (resultSet != null && resultSet.next()) {
                        // Kullanıcı zaten var
                        LoginController.showAlert("Registration Failed", "E-mail is already registered!", Alert.AlertType.ERROR);
                        System.out.println("E-mail is already registered.");
                        return;
                    }
                }
            }

            String insertUserQuery = "INSERT INTO users (email, password_hash, username, birthday) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery)) {
                insertUserStatement.setString(1, email);
                insertUserStatement.setString(2, PasswordUtils.hashPassword(password));
                insertUserStatement.setString(3, username);
                insertUserStatement.setDate(4, birthday);

                int rowsInserted = insertUserStatement.executeUpdate();
                if (rowsInserted > 0) {
                    LoginController.showAlert("Registration Successful", "You have been registered!", Alert.AlertType.INFORMATION);
                    System.out.println("Registration successful.");
                } else {
                    LoginController.showAlert("Registration Failed", "Failed to register the user!", Alert.AlertType.ERROR);
                    System.out.println("Registration failed.");
                }
            }

        } catch (SQLException e) {
            LoginController.showAlert("Database Error", "An error: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println("Database Error: " + e.getMessage());
        }
    }


    @FXML
    void goLoginScreen(MouseEvent event) throws IOException {
        PrimaryStage.loginScreen();

    }
}
