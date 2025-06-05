package com.migate.browser;

import com.migate.components.InPageSearchBar;
import com.migate.managers.*;
import com.migate.views.DevToolsView;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.*;

public class TabView {
    private final WebView webView;
    private final WebEngine engine;
    private final Tab tab;
    private final HistoryManager historyManager;
    private final BookmarkManager bookmarkManager;
    private final NotificationManager notificationManager;
    private final DevToolsView devToolsView;
    private InPageSearchBar searchBar;

    public TabView(HistoryManager historyManager, BookmarkManager bookmarkManager,
                   NotificationManager notificationManager, DevToolsView devToolsView,
                   String url) {
        this.historyManager = historyManager;
        this.bookmarkManager = bookmarkManager;
        this.notificationManager = notificationManager;
        this.devToolsView = devToolsView;

        webView = new WebView();
        webView.setContextMenuEnabled(false);
        engine = webView.getEngine();

        tab = new Tab();
        tab.setContent(createContentPane());
        updateTitle();

        // Setup web engine.will move to chromium based later on for better render handling
        engine.locationProperty().addListener((obs, old, newUrl) -> {
            updateTitle();
            historyManager.addHistoryEntry(newUrl, engine.getTitle());
        });

        engine.getLoadWorker().stateProperty().addListener((obs, old, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                devToolsView.logPageLoad(engine.getLocation());
            }
        });

        engine.setOnAlert(event -> notificationManager.showNotification("Page Alert", event.getData()));
        engine.setPromptHandler(data -> notificationManager.showInputDialog(data.getMessage()));

        // Load initial URL ie google.com
        engine.load(url);
    }

    private StackPane createContentPane() {
        searchBar = new InPageSearchBar(webView.getEngine());
        searchBar.setVisible(false);

        StackPane contentPane = new StackPane(webView, searchBar);
        StackPane.setAlignment(searchBar, Pos.TOP_CENTER);
        return contentPane;
    }

    public void showSearchBar() {
        searchBar.setVisible(true);
        searchBar.focusSearchField();
    }

    private void updateTitle() {
        String title = engine.getTitle();
        tab.setText(title != null && !title.isEmpty() ? title : "New Tab");
    }

    public Tab getTab() { return tab; }
    public WebEngine getEngine() { return engine; }
    public WebView getWebView() { return webView; }
}
