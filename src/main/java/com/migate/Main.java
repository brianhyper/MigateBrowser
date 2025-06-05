package com.migate;

import com.migate.browser.BrowserController;
import com.migate.views.WelcomeScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        Scene scene = new Scene(welcomeScreen, 1200, 800);
        scene.setFill(null);
        scene.getStylesheets().add(getClass().getResource("/themes/neon-theme.css").toExternalForm());
//Bora iwake walali😅😅

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Migate Browser");
        primaryStage.show();
//loading the welcome scren
        welcomeScreen.setOnLoadFinished(e -> {
            BrowserController browser = new BrowserController(primaryStage);
            Scene browserScene = new Scene(browser, 1200, 800);
            browserScene.getStylesheets().add(getClass().getResource("/themes/neon-theme.css").toExternalForm());
            primaryStage.setScene(browserScene);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}