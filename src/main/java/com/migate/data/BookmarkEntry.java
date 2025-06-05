package com.migate.data;

public class BookmarkEntry {
    private final String url;
    private final String title;
    private final String folder;

    public BookmarkEntry(String url, String title, String folder) {
        this.url = url;
        this.title = title;
        this.folder = folder;
    }

    // getters
    public String getUrl() { return url; }
    public String getTitle() { return title; }
    public String getFolder() { return folder; }
}