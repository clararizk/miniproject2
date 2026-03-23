package com.example.demoproject2;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

    public class ClientController implements Initializable {

        @FXML private TableView<Client> clientTable;
        @FXML private TableColumn<Client, Integer> colId;
        @FXML private TableColumn<Client, String> colName;
        @FXML private TableColumn<Client, String> colEmail;
        @FXML private TableColumn<Client, String> colPhone;
        @FXML private TableColumn<Client, String> colAddress;

        @FXML private TextField tfName;
        @FXML private TextField tfEmail;
        @FXML private TextField tfPhone;
        @FXML private TextField tfAddress;
        @FXML private TextField tfSearch;

        @FXML private Button btnAdd;
        @FXML private Button btnUpdate;
        @FXML private Button btnDelete;
        @FXML private Button btnClear;

        @FXML private Label lblStatus;

        private final ClientStore store = ClientStore.getInstance();
        private Client selectedClient = null;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            clientTable.setItems(store.getClients());

            FilteredList<Client> filteredData = new FilteredList<>(store.getClients(), p -> true);
            tfSearch.textProperty().addListener((obs, oldVal, newVal) -> {
                filteredData.setPredicate(client -> {
                    if (newVal == null || newVal.trim().isEmpty()) {
                        return true;
                    }
                    String lower = newVal.toLowerCase().trim();
                    return String.valueOf(client.getId()).contains(lower)
                            || client.getName().toLowerCase().contains(lower)
                            || client.getEmail().toLowerCase().contains(lower)
                            || client.getPhone().toLowerCase().contains(lower)
                            || client.getAddress().toLowerCase().contains(lower);
                });
            });
            clientTable.setItems(filteredData);

            clientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
                selectedClient = newSel;
                if (newSel != null) {
                    tfName.setText(newSel.getName());
                    tfEmail.setText(newSel.getEmail());
                    tfPhone.setText(newSel.getPhone());
                    tfAddress.setText(newSel.getAddress());
                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(false);
                    btnDelete.setDisable(false);
                } else {
                    btnAdd.setDisable(false);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                }
            });

            btnAdd.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        }

        @FXML
        private void handleAdd() {
            if (!validateForm()) return;

            Client newClient = new Client(
                    0,
                    tfName.getText().trim(),
                    tfEmail.getText().trim(),
                    tfPhone.getText().trim(),
                    tfAddress.getText().trim()
            );

            store.addClient(newClient);
            clearForm();
            lblStatus.setText("Client added successfully.");
        }

        @FXML
        private void handleUpdate() {
            if (selectedClient == null) {
                lblStatus.setText("Please select a client.");
                return;
            }
            if (!validateForm()) return;

            store.updateClient(
                    selectedClient,
                    tfName.getText().trim(),
                    tfEmail.getText().trim(),
                    tfPhone.getText().trim(),
                    tfAddress.getText().trim()
            );

            clientTable.refresh();
            clearForm();
            lblStatus.setText("Client updated successfully.");
        }

        @FXML
        private void handleDelete() {
            if (selectedClient == null) {
                lblStatus.setText("Please select a client.");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Delete Client");
            confirm.setHeaderText("Delete \"" + selectedClient.getName() + "\"?");
            confirm.setContentText("This action cannot be undone.");

            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                store.deleteClient(selectedClient);
                clearForm();
                lblStatus.setText("Client deleted successfully.");
            }
        }

        @FXML
        private void handleClear() {
            clearForm();
            lblStatus.setText("");
        }

        @FXML
        private void goHome() {
            SceneManager.switchScene("HomeView.fxml", "Home");
        }

        private boolean validateForm() {
            if (tfName.getText().trim().isEmpty()) return false;
            if (tfEmail.getText().trim().isEmpty()) return false;
            if (tfPhone.getText().trim().isEmpty()) return false;
            if (tfAddress.getText().trim().isEmpty()) return false;
            return true;
        }

        private void clearForm() {
            tfName.clear();
            tfEmail.clear();
            tfPhone.clear();
            tfAddress.clear();
            tfSearch.clear();
            clientTable.getSelectionModel().clearSelection();
            selectedClient = null;
            btnAdd.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);}}

