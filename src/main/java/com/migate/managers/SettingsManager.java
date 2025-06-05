package com.migate.managers;

import java.util.prefs.Preferences;

public class SettingsManager {
    private static final String THEME_KEY = "theme";
    private static final String HOME_PAGE_KEY = "homepage";
    private static final String DARK_MODE_KEY = "dark_mode";
    private final Preferences prefs = Preferences.userNodeForPackage(SettingsManager.class);

    public String getCurrentTheme() {
        return prefs.get(THEME_KEY, "neon-theme");
    }

    public void setTheme(String theme) {
        prefs.put(THEME_KEY, theme);
    }

    public static String getHomePage() {
        return Preferences.userNodeForPackage(SettingsManager.class)
                .get(HOME_PAGE_KEY, "https://www.google.com");
    }

    public void setHomePage(String homePage) {
        prefs.put(HOME_PAGE_KEY, homePage);
    }

    public boolean isDarkMode() {
        return prefs.getBoolean(DARK_MODE_KEY, true);
    }

    public void setDarkMode(boolean darkMode) {
        prefs.putBoolean(DARK_MODE_KEY, darkMode);
    }
}