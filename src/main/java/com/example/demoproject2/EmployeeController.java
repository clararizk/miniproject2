package com.example.demoproject2;

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeController {

    @FXML private TableView<Employee> employeeTable;
    @FXML private TableColumn<Employee, Number> colId;
    @FXML private TableColumn<Employee, String> colName;
    @FXML private TableColumn<Employee, String> colEmail;
    @FXML private TableColumn<Employee, String> colDepartment;
    @FXML private TableColumn<Employee, Number> colSalary;

    @FXML private TextField tfId;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfDepartment;
    @FXML private TextField tfSalary;
    @FXML private TextField tfSearch;

    private final EmployeeStore store = EmployeeStore.getInstance();
    private FilteredList<Employee> filtered;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colEmail.setCellValueFactory(data -> data.getValue().emailProperty());
        colDepartment.setCellValueFactory(data -> data.getValue().departmentProperty());
        colSalary.setCellValueFactory(data -> data.getValue().salaryProperty());

        loadEmployees();

        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, emp) -> {
            if (emp != null) {
                tfId.setText(String.valueOf(emp.getId()));
                tfName.setText(emp.getName());
                tfEmail.setText(emp.getEmail());
                tfDepartment.setText(emp.getDepartment());
                tfSalary.setText(String.valueOf(emp.getSalary()));
            }
        });
    }

    private void loadEmployees() {
        filtered = new FilteredList<>(store.getEmployees(), p -> true);

        tfSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            filtered.setPredicate(emp -> {
                if (newVal == null || newVal.trim().isEmpty()) {
                    return true;
                }

                String search = newVal.toLowerCase().trim();

                return String.valueOf(emp.getId()).contains(search)
                        || emp.getName().toLowerCase().contains(search)
                        || emp.getEmail().toLowerCase().contains(search)
                        || emp.getDepartment().toLowerCase().contains(search)
                        || String.valueOf(emp.getSalary()).contains(search);
            });
        });

        employeeTable.setItems(filtered);
    }

    @FXML
    private void handleAdd() {
        try {
            int id = Integer.parseInt(tfId.getText().trim());
            double salary = Double.parseDouble(tfSalary.getText().trim());

            if (store.idExists(id)) {
                showAlert("ID already exists!");
                return;
            }

            Employee emp = new Employee(
                    id,
                    tfName.getText().trim(),
                    tfEmail.getText().trim(),
                    tfDepartment.getText().trim(),
                    salary
            );

            store.addEmployee(emp);
            loadEmployees();
            clearFields();

        } catch (Exception e) {
            showAlert("Invalid input!");
        }
    }

    @FXML
    private void handleUpdate() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an employee.");
            return;
        }

        try {
            int id = Integer.parseInt(tfId.getText().trim());
            double salary = Double.parseDouble(tfSalary.getText().trim());

            if (store.idExistsExcluding(id, selected)) {
                showAlert("ID already exists!");
                return;
            }

            Employee updated = new Employee(
                    id,
                    tfName.getText().trim(),
                    tfEmail.getText().trim(),
                    tfDepartment.getText().trim(),
                    salary
            );

            store.updateEmployee(selected, updated);
            loadEmployees();
            clearFields();

        } catch (Exception e) {
            showAlert("Invalid input!");
        }
    }

    @FXML
    private void handleDelete() {
        Employee selected = employeeTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an employee.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Employee");
        confirm.setHeaderText("Delete \"" + selected.getName() + "\"?");
        confirm.setContentText("This action cannot be undone.");

        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            store.deleteEmployee(selected);
            loadEmployees();
            clearFields();
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    @FXML
    private void goHome() {
        SceneManager.switchScene("HomeView.fxml", "Home");
    }

    private void clearFields() {
        tfId.clear();
        tfName.clear();
        tfEmail.clear();
        tfDepartment.clear();
        tfSalary.clear();
        tfSearch.clear();
        employeeTable.getSelectionModel().clearSelection();
    }

    private void showAlert(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).show();
    }
}