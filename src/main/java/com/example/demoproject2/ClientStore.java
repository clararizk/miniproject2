package com.example.demoproject2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientStore {

    private static ClientStore instance;
    private final ObservableList<Client> clients;
    private int nextId = 1;

    private ClientStore() {
        clients = FXCollections.observableArrayList();

        clients.add(new Client(nextId++, "John Doe", "john@gmail.com", "70123456", "Beirut"));
        clients.add(new Client(nextId++, "Jane Smith", "jane@gmail.com", "71111222", "Jounieh"));
    }

    public static ClientStore getInstance() {
        if (instance == null) {
            instance = new ClientStore();
        }
        return instance;
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        client.setId(nextId++);
        clients.add(client);
    }

    public void updateClient(Client original, String name, String email, String phone, String address) {
        original.setName(name);
        original.setEmail(email);
        original.setPhone(phone);
        original.setAddress(address);
    }

    public void deleteClient(Client client) {
        clients.remove(client);
    }
}
