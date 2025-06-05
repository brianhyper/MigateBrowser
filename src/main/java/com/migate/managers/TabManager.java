package com.migate.managers;

import com.migate.browser.TabView;
import com.migate.components.NavigationBar;
import com.migate.components.StatusBar;
import com.migate.views.DevToolsView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabManager {
    private final TabPane tabPane = new TabPane();
    private final NavigationBar navigationBar;
    private final StatusBar statusBar;
    private final HistoryManager historyManager;
    private final BookmarkManager bookmarkManager;
    private final SettingsManager settingsManager;
    private final NotificationManager notificationManager;
    private final DevToolsView devToolsView;

    public TabManager(NavigationBar navigationBar, StatusBar statusBar,
                      HistoryManager historyManager, BookmarkManager bookmarkManager,
                      SettingsManager settingsManager, NotificationManager notificationManager,
                      DevToolsView devToolsView) {
        this.navigationBar = navigationBar;
        this.statusBar = statusBar;
        this.historyManager = historyManager;
        this.bookmarkManager = bookmarkManager;
        this.settingsManager = settingsManager;
        this.notificationManager = notificationManager;
        this.devToolsView = devToolsView;

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        addNewTab(settingsManager.getHomePage());
    }

    public void addNewTab() {
        addNewTab(settingsManager.getHomePage());
    }

    public void addNewTab(String url) {
        TabView tabView = new TabView(
                historyManager,
                bookmarkManager,
                notificationManager,
                devToolsView,
                url
        );

        Tab tab = tabView.getTab();
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        navigationBar.bindTab(tabView);

        // Update status bar
        tabView.getEngine().getLoadWorker().progressProperty().addListener(
                (obs, old, progress) -> statusBar.updateProgress(progress.doubleValue())
        );

        tabView.getEngine().titleProperty().addListener(
                (obs, old, title) -> statusBar.updateStatus(title)
        );
    }

    public TabView getCurrentTab() {
        return (TabView) tabPane.getSelectionModel().getSelectedItem().getUserData();
    }

    public void closeTab(Tab tab) {
        tabPane.getTabs().remove(tab);
        if (tabPane.getTabs().isEmpty()) {
            addNewTab();
        }
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}