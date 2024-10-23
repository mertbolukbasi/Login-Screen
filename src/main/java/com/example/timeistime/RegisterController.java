package com.example.timeistime;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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

    }

    @FXML
    void goLoginScreen(MouseEvent event) throws IOException {
        PrimaryStage.loginScreen();

    }
}
