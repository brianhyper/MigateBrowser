package com.migate.components;

import com.migate.browser.TabView;
import com.migate.managers.BookmarkManager;
import com.migate.managers.SettingsManager;
import com.migate.views.BookmarksView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class NavigationBar extends HBox {
    private TextField omnibox;
    private TabView currentTab;
    private final BookmarkManager bookmarkManager;

    public NavigationBar(BookmarkManager bookmarkManager) {
        this.bookmarkManager = bookmarkManager;

        setSpacing(10);
        getStyleClass().add("navigation-bar");
        //learnt about this today
        Button back = createIconButton("←");
        Button forward = createIconButton("→");
        Button reload = createIconButton("↻");
        Button stop = createIconButton("⏹");
        Button home = createIconButton("⌂");
        Button bookmarks = createIconButton("★");
        Button history = createIconButton("🕒");
        Button devTools = createIconButton("</>");
        Button settings = createIconButton("⚙");

        omnibox = new TextField();
        omnibox.setPromptText("Search or enter URL");
        omnibox.getStyleClass().add("omnibox");
        HBox.setHgrow(omnibox, Priority.ALWAYS);

        getChildren().addAll(back, forward, reload, stop, home, omnibox,
                bookmarks, history, devTools, settings);

        // Button actions
        back.setOnAction(e -> currentTab.getEngine().getHistory().go(-1));
        forward.setOnAction(e -> currentTab.getEngine().getHistory().go(1));
        reload.setOnAction(e -> currentTab.getEngine().reload());
        stop.setOnAction(e -> currentTab.getEngine().getLoadWorker().cancel());
        home.setOnAction(e -> currentTab.getEngine().load(SettingsManager.getHomePage()));
        bookmarks.setOnAction(e -> showBookmarksView());
        history.setOnAction(e -> showHistoryView());
        settings.setOnAction(e -> showSettingsView());
        devTools.setOnAction(e -> toggleDevTools());

        omnibox.setOnAction(e -> navigateToAddress());
    }

    public void bindTab(TabView tabView) {
        this.currentTab = tabView;
        omnibox.setText(tabView.getEngine().getLocation());
        tabView.getEngine().locationProperty().addListener((obs, old, newUrl) ->
                omnibox.setText(newUrl));
    }

    private void navigateToAddress() {
        String text = omnibox.getText();
        if (!text.startsWith("http://") && !text.startsWith("https://")) {
            text = "https://" + text;
        }
        currentTab.getEngine().load(text);
    }

    private Button createIconButton(String icon) {
        Button btn = new Button(icon);
        btn.getStyleClass().add("neon-button");
        return btn;
    }

    private void showBookmarksView() {
        Stage stage = new Stage();
        stage.setTitle("Bookmarks");

        BookmarksView bookmarksView = new BookmarksView(bookmarkManager);
        Scene scene = new Scene(bookmarksView, 400, 600);

        stage.setScene(scene);
        stage.show();
    }

    // Stub methods to avoid compilation errors.
    private void showHistoryView() {
        // Implement or stub.
    }

    private void showSettingsView() {
        // Implement or stub.
    }

    private void toggleDevTools() {
        // Implement or stub.
    }
}
