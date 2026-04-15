package com.example.demoproject2;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class SceneManager {

    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxmlFile, String title) {
        try {
            URL location = SceneManager.class.getResource("/com/example/demoproject2/" + fxmlFile);

            if (location == null) {
                System.out.println("Cannot find FXML file: " + fxmlFile);
                return;
            }

            Parent root = FXMLLoader.load(location);
            Scene scene = new Scene(root);

            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
