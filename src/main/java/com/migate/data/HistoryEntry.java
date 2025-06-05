package com.migate.data;

public class HistoryEntry {
    private final String url;
    private final String title;
    private final String timestamp;

    public HistoryEntry(String url, String title, String timestamp) {
        this.url = url;
        this.title = title;
        this.timestamp = timestamp;
    }

    // getters
    public String getUrl() { return url; }
    public String getTitle() { return title; }
    public String getTimestamp() { return timestamp; }
}