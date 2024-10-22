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

    // Giriş butonuna tıklanınca çalışacak
    @FXML
    void getUserLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Boş alan kontrolü
        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Empty Areas!", "Enter the e-mail and password!", Alert.AlertType.WARNING);
            return;
        }

        // Veritabanı ile giriş kontrolü
        try (Connection connection = Database.openDataBase()) {
            assert connection != null;
            try (PreparedStatement preparedStatement = createPreparedStatement(connection, email, password);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet != null && resultSet.next()) {
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

    // PreparedStatement oluşturan yardımcı metot
    private PreparedStatement createPreparedStatement(Connection connection, String email, String password) throws SQLException {
        String sql = "SELECT * FROM logindata WHERE Emails = ? AND Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    // Genel bir alert gösterici
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
