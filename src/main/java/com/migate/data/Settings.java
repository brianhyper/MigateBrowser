package com.migate.data;

import java.util.prefs.Preferences;
// most methods are under development so haziwork
public class Settings {
    private static final String THEME_KEY = "theme";
    private static final String HOME_PAGE_KEY = "homepage";
    private static final String DARK_MODE_KEY = "dark_mode";
    private static final String ZOOM_LEVEL_KEY = "zoom_level";

    private final Preferences prefs;

    public Settings() {
        prefs = Preferences.userNodeForPackage(Settings.class);
    }

    public String getTheme() {
        return prefs.get(THEME_KEY, "neon-theme");
    }

    public void setTheme(String theme) {
        prefs.put(THEME_KEY, theme);
    }

    public String getHomePage() {
        return prefs.get(HOME_PAGE_KEY, "https://www.google.com");
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

    public double getZoomLevel() {
        return prefs.getDouble(ZOOM_LEVEL_KEY, 1.0);
    }

    public void setZoomLevel(double zoomLevel) {
        prefs.putDouble(ZOOM_LEVEL_KEY, zoomLevel);
    }
}