package comp3111.webscraper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

public class TableController {

    private TableView<Item> tableView;

    private TableColumn<Item, String> tableColumnTitle;
    private TableColumn<Item, Double> tableColumnPrice;
    private TableColumn<Item, String> tableColumnURL;
    private TableColumn<Item, Date> tableColumnPostedDate;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    @SuppressWarnings("unchecked")
    public void initialize(TableView<Item> tableView) {
        this.tableView = tableView;
        ObservableList<TableColumn<Item, ?>> tableColumns = tableView.getColumns();
        this.tableColumnTitle = (TableColumn<Item, String>) tableColumns.get(0);
        this.tableColumnPrice = (TableColumn<Item, Double>) tableColumns.get(1);
        this.tableColumnURL = (TableColumn<Item, String>) tableColumns.get(2);
        this.tableColumnPostedDate = (TableColumn<Item, Date>) tableColumns.get(3);

        this.tableColumnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.tableColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.tableColumnURL.setCellValueFactory(new PropertyValueFactory<>("url"));
        this.tableColumnPostedDate.setCellValueFactory(new PropertyValueFactory<>("postedDate"));
    }

    public void updateItemList(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        updateTable();
    }

    private void updateTable() {
        this.tableView.setItems(this.items);
    }
}
