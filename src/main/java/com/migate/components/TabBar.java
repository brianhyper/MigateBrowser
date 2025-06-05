package com.migate.components;

import com.migate.managers.TabManager;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class TabBar extends HBox {
    private final TabPane tabPane;
    private TabManager tabManager;
    private final Button newTabButton;

    public TabBar() {
        setSpacing(5);
        getStyleClass().add("tab-bar");

        newTabButton = new Button("+");
        newTabButton.getStyleClass().add("neon-button");
        newTabButton.setOnAction(e -> tabManager.addNewTab());

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        tabPane.setTabMinWidth(100);
        tabPane.setTabMaxWidth(200);
        HBox.setHgrow(tabPane, Priority.ALWAYS);

        getChildren().addAll(tabPane, newTabButton);
    }

    public void setTabManager(TabManager tabManager) {
        this.tabManager = tabManager;
        tabPane.getTabs().setAll(tabManager.getTabPane().getTabs());
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null) {
                tabManager.getTabPane().getSelectionModel().select(newTab);
            }
        });
    }

    public void addTabButton(Button button) {
        getChildren().add(button);
    }
}