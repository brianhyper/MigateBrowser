package com.migate.util;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class ThemeUtils {
    public static void applyTheme(String theme, Parent root) {
        Scene scene = root.getScene();
        if (scene == null) return;

        scene.getStylesheets().clear();
        scene.getStylesheets().add(ThemeUtils.class.getResource("/themes/" + theme + ".css").toExternalForm());

        if (theme.equals("dark-theme")) {
            scene.getRoot().setStyle("-fx-base: #2d2d3c;");
        } else if (theme.equals("light-theme")) {
            scene.getRoot().setStyle("-fx-base: #e0e0f0;");
        }
    }
}