package com.migate.views;

import com.migate.managers.SettingsManager;
import com.migate.util.ThemeUtils;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;


// under development (😥😥)
public class SettingsView extends VBox {
    public SettingsView(SettingsManager settingsManager) {
        getStyleClass().add("glass-pane");
        setSpacing(20);
        setPadding(new Insets(20));

        Label title = new Label("Browser Settings");
        title.getStyleClass().add("title");

        // Theme selection
        Label themeLabel = new Label("Theme:");
        ComboBox<String> themeCombo = new ComboBox<>();
        themeCombo.getItems().addAll("neon-theme", "dark-theme", "light-theme");
        themeCombo.setValue(settingsManager.getCurrentTheme());

        // Home page
        Label homeLabel = new Label("Home Page:");
        TextField homeField = new TextField(SettingsManager.getHomePage());

        // Dark mode toggle
        Label darkModeLabel = new Label("Dark Mode:");
        ToggleSwitch darkModeToggle = new ToggleSwitch();
        darkModeToggle.setSelected(settingsManager.isDarkMode());

        // Save button
        Button saveBtn = new Button("Save Settings");
        saveBtn.getStyleClass().add("neon-button");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.addRow(0, themeLabel, themeCombo);
        grid.addRow(1, homeLabel, homeField);
        grid.addRow(2, darkModeLabel, darkModeToggle);

        getChildren().addAll(title, grid, saveBtn);

        // Save handler
        saveBtn.setOnAction(e -> {
            settingsManager.setTheme(themeCombo.getValue());
            settingsManager.setHomePage(homeField.getText());
            settingsManager.setDarkMode(darkModeToggle.isSelected());
            ThemeUtils.applyTheme(themeCombo.getValue(), this);
        });
    }

    static class ToggleSwitch extends ToggleButton {
        public ToggleSwitch() {
            setMinWidth(60);
            setMinHeight(30);

            selectedProperty().addListener((obs, old, selected) -> {
                if (selected) {
                    setText("ON");
                    setStyle("-fx-background-color: #00ffff;");
                } else {
                    setText("OFF");
                    setStyle("-fx-background-color: #666;");
                }
            });
            setSelected(false);
        }
    }
}