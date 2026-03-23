package com.example.demoproject2;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Label lblMessage;

    @FXML
    private void handleLogin() {
        String username = tfUsername.getText().trim();
        String password = pfPassword.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            lblMessage.setText("Please enter username and password.");
            return;
        }

        boolean success = AuthService.login(username, password);

        if (success) {
            lblMessage.setText("");
            SceneManager.switchScene("HomeView.fxml", "Home");
        } else {
            lblMessage.setText("Invalid username or password.");
        }
    }

    @FXML
    private void handleClear() {
        tfUsername.clear();
        pfPassword.clear();
        lblMessage.setText("");
    }
}
