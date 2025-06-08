package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Notification {
    public static void showInfo(String title, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void showPage(Stage stage, String title, String message) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center; -fx-padding: 40;");
        Label label = new Label(message);
        root.getChildren().add(label);

        Scene scene = new Scene(root, 300, 150);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}