package com.migate.browser;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BrowserEngine {
    private final WebView webView;
    private final WebEngine webEngine;

    public BrowserEngine() {
        webView = new WebView();
        webEngine = webView.getEngine();
        configureEngine();
    }

    private void configureEngine() {
        webEngine.setJavaScriptEnabled(true);
        webEngine.setUserStyleSheetLocation(getClass().getResource("/themes/browser.css").toString());
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");
    }
    //protocols
    public void loadUrl(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        webEngine.load(url);
    }

    public WebView getWebView() {
        return webView;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }
}