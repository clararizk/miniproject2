package com.example.demoproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;

public class ProductStore {

    private static ProductStore instance;
    private final ObservableList<Product> products;
    private int nextId = 1;

    private ProductStore() {
        products = FXCollections.observableArrayList();

        products.add(new Product(nextId++, "Laptop", 50, 1200, "Electronics", "Tech Supplier", LocalDate.now()));
        products.add(new Product(nextId++, "Phone", 100, 800, "Electronics", "Mobile Inc.", LocalDate.now()));
    }

    public static ProductStore getInstance() {
        if (instance == null) {
            instance = new ProductStore();
        }
        return instance;
    }

    public ObservableList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        p.setId(nextId++);
        products.add(p);
    }

    public void deleteProduct(Product p) {
        products.remove(p);
    }
}