package com.migate.managers;

import com.migate.data.HistoryEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HistoryManager {
    private static final String HISTORY_FILE = "data/history.json";
    private List<HistoryEntry> history = new ArrayList<>();

    public HistoryManager() {
        loadHistory();
    }

    public void addHistoryEntry(String url, String title) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        history.add(0, new HistoryEntry(url, title, timestamp));
        saveHistory();
    }

    public List<HistoryEntry> getHistory() {
        return new ArrayList<>(history);
    }

    public List<HistoryEntry> searchHistory(String query) {
        List<HistoryEntry> results = new ArrayList<>();
        for (HistoryEntry entry : history) {
            if (entry.getUrl().contains(query) || entry.getTitle().contains(query)) {
                results.add(entry);
            }
        }
        return results;
    }

    public void removeHistoryEntry(HistoryEntry entry) {
        history.remove(entry);
        saveHistory();
    }

    private void loadHistory() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(HISTORY_FILE)));
            history = new Gson().fromJson(json, new TypeToken<List<HistoryEntry>>(){}.getType());
        } catch (IOException e) {
            history = new ArrayList<>();
        }
    }

    private void saveHistory() {
        try {
            File file = new File(HISTORY_FILE);

            // Ensure the parent directory exists
            File parent = file.getParentFile();
            if (!parent.exists()) {
                boolean created = parent.mkdirs();
                System.out.println("Created 'data/' directory: " + created);
            }

            // Write history to file
            try (FileWriter writer = new FileWriter(file)) {
                new Gson().toJson(history, writer);
            }

            System.out.println("History saved to: " + file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
