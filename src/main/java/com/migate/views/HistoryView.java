package com.migate.views;

import com.migate.data.HistoryEntry;
import com.migate.managers.HistoryManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

// under development (iza wings)
public class HistoryView extends VBox {
    public HistoryView(HistoryManager historyManager) {
        getStyleClass().add("glass-pane");
        setSpacing(10);
        setPadding(new javafx.geometry.Insets(10));

        Label title = new Label("History");
        title.getStyleClass().add("title");

        TextField searchField = new TextField();
        searchField.setPromptText("Search history...");
        searchField.getStyleClass().add("search-field");

        TableView<HistoryEntry> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<HistoryEntry, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<HistoryEntry, String> urlCol = new TableColumn<>("URL");
        urlCol.setCellValueFactory(new PropertyValueFactory<>("url"));

        TableColumn<HistoryEntry, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        table.getColumns().addAll(titleCol, urlCol, timeCol);

        FilteredList<HistoryEntry> filteredList = new FilteredList<>(
                FXCollections.observableArrayList(historyManager.getHistory()), p -> true);

        searchField.textProperty().addListener((obs, old, newVal) ->
                filteredList.setPredicate(entry ->
                        entry.getTitle().toLowerCase().contains(newVal.toLowerCase()) ||
                                entry.getUrl().toLowerCase().contains(newVal.toLowerCase()))
        );

        SortedList<HistoryEntry> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

        // Context menu for history items
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(e -> {
            HistoryEntry selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                historyManager.removeHistoryEntry(selected);
                filteredList.getSource().remove(selected);
            }
        });
        contextMenu.getItems().add(deleteItem);
        table.setContextMenu(contextMenu);

        getChildren().addAll(title, searchField, table);
    }

    // method to remove history entry from manager
    private void removeHistoryEntry(HistoryManager manager, HistoryEntry entry) {

    }
}