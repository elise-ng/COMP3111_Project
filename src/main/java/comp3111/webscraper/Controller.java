/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;


/**
 * 
 * @author kevinw
 *
 *
 * Controller class that manage GUI interaction. Please see document about JavaFX for details.
 * 
 */
public class Controller {

    @FXML
    private Label labelCount; 

    @FXML 
    private Label labelPrice; 

    @FXML 
    private Hyperlink labelMin; 

    @FXML 
    private Hyperlink labelLatest; 

    @FXML
    private TextField textFieldKeyword;
    
    @FXML
    private TextArea textAreaConsole;

    @FXML
    private TableView<Item> tableView;
    
    private WebScraper scraper;
    private TableController tableController;
    
    private Summary summary;
    /**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    	tableController = new TableController();
    	summary = new Summary();
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {
    	tableController.initialize(tableView);
    	summary.resultNotFound(labelCount, labelPrice, labelMin, labelLatest);
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	if (result.isEmpty()) {
    	    output += "No item found.";
        } else {
            for (Item item : result) {
                output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\t" + item.getPostedDate() + "\t" + item.getSourcePortal() + "\n";
            }
        }
    	textAreaConsole.setText(output);

        tableController.updateItemList(result);
        
        summary.updateSummary(result,labelCount, labelPrice, labelMin, labelLatest);
    }
    
    /**
     * Called when the new button is pressed. Very dummy action - print something in the command prompt.
     */
    @FXML
    private void actionNew() {
    	System.out.println("actionNew");
    }
}

