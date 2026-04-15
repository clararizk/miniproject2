package com.example.demoproject2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ProductController {

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    @FXML private TableColumn<Product, String> categoryColumn;
    @FXML private TableColumn<Product, String> supplierColumn;

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private TextField priceField;
    @FXML private TextField categoryField;
    @FXML private TextField supplierField;

    private final ProductStore store = ProductStore.getInstance();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        quantityColumn.setCellValueFactory(data -> data.getValue().quantityProperty().asObject());
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty().asObject());
        categoryColumn.setCellValueFactory(data -> data.getValue().categoryProperty());
        supplierColumn.setCellValueFactory(data -> data.getValue().supplierProperty());

        productTable.setItems(store.getProducts());
    }

    @FXML
    public void addProduct() {
        try {
            Product p = new Product(
                    0,
                    nameField.getText(),
                    Integer.parseInt(quantityField.getText()),
                    Double.parseDouble(priceField.getText()),
                    categoryField.getText(),
                    supplierField.getText(),
                    LocalDate.now()
            );

            store.addProduct(p);
            clearFields();

        } catch (Exception e) {
            showAlert("Invalid input");
        }
    }

    @FXML
    public void deleteProduct() {
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            store.deleteProduct(selected);
        }
    }

    @FXML
    public void handleRowSelect() {
        Product p = productTable.getSelectionModel().getSelectedItem();
        if (p != null) {
            nameField.setText(p.getName());
            quantityField.setText(String.valueOf(p.getQuantity()));
            priceField.setText(String.valueOf(p.getPrice()));
            categoryField.setText(p.getCategory());
            supplierField.setText(p.getSupplier());
        }
    }

    @FXML
    private void goHome() {
        SceneManager.switchScene("HomeView.fxml", "Home");
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
        categoryField.clear();
        supplierField.clear();
    }

    private void showAlert(String msg) {
        Alert a = new Alert(Alert.AlertType.WARNING, msg);
        a.showAndWait();
    }
}