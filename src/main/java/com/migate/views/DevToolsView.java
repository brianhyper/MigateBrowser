package com.migate.views;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;

// under development (😥😥)

public class DevToolsView extends VBox {
    private final TextArea console = new TextArea();
    private final TextArea networkLog = new TextArea();

    public DevToolsView() {
        getStyleClass().add("glass-pane");
        setSpacing(10);

        TabPane tabPane = new TabPane();

        Tab consoleTab = new Tab("Console", console);
        Tab networkTab = new Tab("Network", networkLog);
        Tab sourceTab = new Tab("Source", new SourceViewer());

        tabPane.getTabs().addAll(consoleTab, networkTab, sourceTab);
        getChildren().add(tabPane);

        console.setEditable(false);
        networkLog.setEditable(false);
    }

    public void logPageLoad(String url) {
        console.appendText("Page loaded: " + url + "\n");
    }

    public void logRequest(String url, String method, int status) {
        networkLog.appendText(method + " " + url + " - " + status + "\n");
    }

    static class SourceViewer extends TextArea {
        public SourceViewer() {
            setEditable(false);
        }

        public void loadSource(WebEngine engine) {
            String html = (String) engine.executeScript("document.documentElement.outerHTML");
            setText(html);
        }
    }
}