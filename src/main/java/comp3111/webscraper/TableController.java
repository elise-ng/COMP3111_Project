package comp3111.webscraper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.List;

/**
 * A controller for adapting scraped items to a table
 */
public class TableController {

    private TableView<Item> tableView;

    private TableColumn<Item, String> tableColumnTitle;
    private TableColumn<Item, Double> tableColumnPrice;
    private TableColumn<Item, String> tableColumnURL;
    private TableColumn<Item, Date> tableColumnPostedDate;

    private ObservableList<Item> items = FXCollections.observableArrayList();

    /**
     * Initialize the TableController with a TableView
     * @param tableView The TableView for the controller
     */
    @SuppressWarnings("unchecked")
    void initialize(TableView<Item> tableView) {
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

        setupCellEventOnClick();
    }

    /**
     * Update items with a new list
     * @param items The new List<Item>
     */
    void updateItemList(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        updateTable();
    }

    /**
     * Set up the OnClick events of URL cells during initialize
     */
    private void setupCellEventOnClick() {
        this.tableColumnURL.setCellFactory(tc -> {
            TableCell<Item, String> cell = new TableCell<Item, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if(!cell.isEmpty()) {
                    String url = cell.getItem();
                    Utils.openURL(url);
                }
            });
            return cell;
        });
    }

    /**
     * Internal method to notify TableView to update with the current list
     */
    private void updateTable() {
        this.tableView.setItems(this.items);
    }
}
