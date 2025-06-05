package com.migate.managers;

import com.migate.data.BookmarkEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class BookmarkManager {
    private static final String BOOKMARKS_FILE = "data/bookmarks.json"; //error ikikudirects hapa, rebuild hii project
    private List<BookmarkEntry> bookmarks = new ArrayList<>();

    public BookmarkManager() {
        loadBookmarks();
    }

    public void addBookmark(String url, String title, String folder) {
        bookmarks.add(new BookmarkEntry(url, title, folder));
        saveBookmarks();
    }

    public void removeBookmark(String url) {
        bookmarks.removeIf(entry -> entry.getUrl().equals(url));
        saveBookmarks();
    }

    public List<BookmarkEntry> getBookmarks() {
        return new ArrayList<>(bookmarks);
    }

    public List<BookmarkEntry> getBookmarksByFolder(String folder) {
        List<BookmarkEntry> result = new ArrayList<>();
        for (BookmarkEntry entry : bookmarks) {
            if (entry.getFolder().equals(folder)) {
                result.add(entry);
            }
        }
        return result;
    }

    public List<String> getFolders() {
        Set<String> folders = new TreeSet<>();
        for (BookmarkEntry entry : bookmarks) {
            folders.add(entry.getFolder());
        }
        return new ArrayList<>(folders);
    }

    private void loadBookmarks() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(BOOKMARKS_FILE)));
            bookmarks = new Gson().fromJson(json, new TypeToken<List<BookmarkEntry>>(){}.getType());
        } catch (IOException e) {
            bookmarks = new ArrayList<>();
        }
    }

    private void saveBookmarks() {
        try (FileWriter writer = new FileWriter(BOOKMARKS_FILE)) {
            new Gson().toJson(bookmarks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}