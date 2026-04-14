package com.example.demoproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeStore {

    private static EmployeeStore instance;
    private final ObservableList<Employee> employees;
    private int nextId = 3;

    private EmployeeStore() {
        employees = FXCollections.observableArrayList();

        employees.add(new Employee(1, "Ali Hassan", "ali@gmail.com", "HR", 1200.0));
        employees.add(new Employee(2, "Sara Nader", "sara@gmail.com", "IT", 1500.0));
    }

    public static EmployeeStore getInstance() {
        if (instance == null) {
            instance = new EmployeeStore();
        }
        return instance;
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee emp) {
        emp.setId(nextId++);
        employees.add(emp);
    }

    public void updateEmployee(Employee target, Employee updated) {
        target.setId(updated.getId());
        target.setName(updated.getName());
        target.setEmail(updated.getEmail());
        target.setDepartment(updated.getDepartment());
        target.setSalary(updated.getSalary());
    }

    public void deleteEmployee(Employee emp) {
        employees.remove(emp);
    }

    public boolean idExists(int id) {
        return employees.stream().anyMatch(e -> e.getId() == id);
    }

    public boolean idExistsExcluding(int id, Employee exclude) {
        return employees.stream()
                .filter(e -> e != exclude)
                .anyMatch(e -> e.getId() == id);
    }
}