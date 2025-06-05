package com.migate.views;

import com.migate.data.BookmarkEntry;
import com.migate.managers.BookmarkManager;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.collections.FXCollections;

import java.util.HashMap;
import java.util.Map;

public class BookmarksView extends VBox {
    public BookmarksView(BookmarkManager manager) {
        getStyleClass().add("glass-pane");

        TreeView<BookmarkEntry> tree = new TreeView<>();
        TreeItem<BookmarkEntry> root = new TreeItem<>(new BookmarkEntry("", "Bookmarks", ""));
        root.setExpanded(true);

        // Group bookmarks by folder
        Map<String, TreeItem<BookmarkEntry>> folders = new HashMap<>();
        for (BookmarkEntry entry : manager.getBookmarks()) {
            TreeItem<BookmarkEntry> parent = folders.computeIfAbsent(
                    entry.getFolder(),
                    k -> new TreeItem<>(new BookmarkEntry("", k, ""))
            );
            parent.getChildren().add(new TreeItem<>(entry));
        }

        root.getChildren().addAll(folders.values());
        tree.setRoot(root);
        tree.setCellFactory(tv -> new BookmarkCell());

        getChildren().add(tree);
    }

    static class BookmarkCell extends TreeCell<BookmarkEntry> {
        @Override
        protected void updateItem(BookmarkEntry item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getTitle());
                if (item.getUrl().isEmpty()) { // the folder
                    setGraphic(new Label("📁"));
                } else {
                    setGraphic(new Label("🔖"));
                }
            }
        }
    }
}