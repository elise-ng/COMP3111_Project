package comp3111.webscraper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class TableController {

    private TableView tableView;

    private TableColumn tableColumnTitle;
    private TableColumn tableColumnPrice;
    private TableColumn tableColumnURL;
    private TableColumn tableColumnPostedDate;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    public void initialize(TableView tableView) {
        this.tableView = tableView;
        ObservableList tableColumns = tableView.getColumns();
        this.tableColumnTitle = (TableColumn) tableColumns.get(0);
        this.tableColumnPrice = (TableColumn) tableColumns.get(1);
        this.tableColumnURL = (TableColumn) tableColumns.get(2);
        this.tableColumnPostedDate = (TableColumn) tableColumns.get(3);
    }

    public void updateItemList(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        updateTable();
    }

    private void updateTable() {
    }
}
