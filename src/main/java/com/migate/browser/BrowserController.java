package com.migate.browser;

import com.migate.components.*;
import com.migate.managers.*;
import com.migate.util.ThemeUtils;
import com.migate.views.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BrowserController extends BorderPane {
    private final TabManager tabManager;
    private final HistoryManager historyManager;
    private final BookmarkManager bookmarkManager;
    private final SettingsManager settingsManager;
    private final NotificationManager notificationManager;
    private final DevToolsView devToolsView;

    public BrowserController(Stage stage) {

        historyManager = new HistoryManager();
        bookmarkManager = new BookmarkManager();
        settingsManager = new SettingsManager();
        notificationManager = new NotificationManager(stage);

        //machoz
        NavigationBar navBar = new NavigationBar(bookmarkManager);
        TabBar tabBar = new TabBar();
        StatusBar statusBar = new StatusBar();
        devToolsView = new DevToolsView();

        // Tab
        tabManager = new TabManager(navBar, statusBar, historyManager, bookmarkManager,
                settingsManager, notificationManager, devToolsView);
        tabBar.setTabManager(tabManager);

        // Dev
        VBox devToolsPane = new VBox(devToolsView);
        devToolsPane.setVisible(false);


        StackPane contentPane = new StackPane(tabManager.getTabPane(), devToolsPane);

        // Setup layout
        setTop(new VBox(tabBar, navBar));
        setCenter(contentPane);
        setBottom(statusBar);

        // Apply theme
        ThemeUtils.applyTheme(settingsManager.getCurrentTheme(), this);

        //
        sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                setupShortcuts(newScene, devToolsPane);
            }
        });
    }

    private void setupShortcuts(Scene scene, VBox devToolsPane) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.isControlDown() && e.getCode() == KeyCode.T) {
                tabManager.addNewTab();
            }
            if (e.isControlDown() && e.getCode() == KeyCode.F) {
                tabManager.getCurrentTab().showSearchBar();
            }
            if (e.isControlDown() && e.getCode() == KeyCode.H) {
                showHistoryView();
            }
            if (e.isControlDown() && e.getCode() == KeyCode.I) {
                devToolsPane.setVisible(!devToolsPane.isVisible());
            }
        });
    }

    private void showHistoryView() {
        HistoryView historyView = new HistoryView(historyManager);
        StackPane modal = new StackPane(historyView);
        modal.setStyle("-fx-background-color: rgba(0,0,0,0.7);");
        getChildren().add(modal);
    }
}
