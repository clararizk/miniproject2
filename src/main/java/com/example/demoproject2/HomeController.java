package com.example.demoproject2;




import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label lblWelcome;

    @FXML
    public void initialize() {
        lblWelcome.setText("Welcome, " + AuthService.getCurrentUser() + "!");
    }

    @FXML
    private void openClients() {
        SceneManager.switchScene("ClientView.fxml", "Clients");
    }

    @FXML
    private void openOrders() {
        showMessage("Orders view is not implemented yet.");
    }

    @FXML
    private void openProducts() {
        showMessage("Products view is not implemented yet.");
    }

    @FXML
    private void openEmployees(){
        SceneManager.switchScene("EmployeeView.fxml", "Employees");
    }
    @FXML
    private void handleLogout() {
        AuthService.logout();
        SceneManager.switchScene("LoginView.fxml", "Login");
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}