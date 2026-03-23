package com.example.demoproject2;

import java.util.HashMap;
import java.util.Map;
public class AuthService {

    private static final Map<String, String> users = new HashMap<>();
    private static String currentUser;

    static {
        users.put("student1", "1111");
        users.put("student2", "2222");
        users.put("student3", "3333");
        users.put("student4", "4444");
    }

    public static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }
}