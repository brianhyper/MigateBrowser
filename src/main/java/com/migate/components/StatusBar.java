package com.migate.components;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {
    private final Label statusLabel;
    private final ProgressBar progressBar;
    private final Label zoomLabel;

    public StatusBar() {
        getStyleClass().add("status-bar");
        setSpacing(10);
        setPrefHeight(25);

        statusLabel = new Label("Ready");
        statusLabel.getStyleClass().add("status-text");

        progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);
        progressBar.setVisible(false);

        zoomLabel = new Label("100%");
        zoomLabel.getStyleClass().add("zoom-label");

        getChildren().addAll(statusLabel, progressBar, zoomLabel);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
        progressBar.setVisible(progress < 1.0 && progress > 0.0);
    }

    public void updateZoom(double zoomLevel) {
        zoomLabel.setText(String.format("%.0f%%", zoomLevel * 100));
    }
}
