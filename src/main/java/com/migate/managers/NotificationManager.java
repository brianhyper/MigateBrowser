package com.migate.managers;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class NotificationManager {
    private final Stage owner;

    public NotificationManager(Stage owner) {
        this.owner = owner;
    }

    public void showNotification(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public String showInputDialog(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initOwner(owner);
        dialog.setTitle("Input Required");
        dialog.setHeaderText(prompt);
        dialog.setContentText("Value:");
        return dialog.showAndWait().orElse("");
    }
}