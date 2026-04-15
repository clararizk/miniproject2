package com.example.demoproject2;

import javafx.beans.property.*;

public class Employee {

    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty department;
    private final DoubleProperty salary;

    public Employee(int id, String name, String email,
                    String department, double salary) {

        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.department = new SimpleStringProperty(department);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public int getId() { return id.get(); }
    public void setId(int value) { id.set(value); }
    public IntegerProperty idProperty() { return id; }

    public String getName() { return name.get(); }
    public void setName(String value) { name.set(value); }
    public StringProperty nameProperty() { return name; }

    public String getEmail() { return email.get(); }
    public void setEmail(String value) { email.set(value); }
    public StringProperty emailProperty() { return email; }

    public String getDepartment() { return department.get(); }
    public void setDepartment(String value) { department.set(value); }
    public StringProperty departmentProperty() { return department; }

    public double getSalary() { return salary.get(); }
    public void setSalary(double value) { salary.set(value); }
    public DoubleProperty salaryProperty() { return salary; }
}