package com.migate.components;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.geometry.Pos;

public class InPageSearchBar extends HBox {
    private final TextField searchField;
    private final WebEngine engine;

    public InPageSearchBar(WebEngine engine) {
        this.engine = engine;
        getStyleClass().add("search-bar");
        setAlignment(Pos.CENTER);
        setSpacing(10);

        searchField = new TextField();
        searchField.setPromptText("Search in page...");
        searchField.setPrefWidth(300);

        Button prevBtn = new Button("▲");
        Button nextBtn = new Button("▼");
        Button closeBtn = new Button("✕");

        prevBtn.getStyleClass().add("neon-button");
        nextBtn.getStyleClass().add("neon-button");
        closeBtn.getStyleClass().add("neon-button");

        getChildren().addAll(searchField, prevBtn, nextBtn, closeBtn);

        // Event handlers
        searchField.textProperty().addListener((obs, old, newVal) -> search(newVal));
        nextBtn.setOnAction(e -> findNext());
        prevBtn.setOnAction(e -> findPrevious());
        closeBtn.setOnAction(e -> setVisible(false));
    }

    public void focusSearchField() {
        searchField.requestFocus();
    }

    private void search(String text) {
        engine.executeScript("window.find('" + text + "')");
    }

    private void findNext() {
        engine.executeScript("window.find('" + searchField.getText() + "', false, true)");
    }

    private void findPrevious() {
        engine.executeScript("window.find('" + searchField.getText() + "', true, true)");
    }
}