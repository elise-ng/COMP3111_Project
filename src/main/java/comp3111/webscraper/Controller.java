/**
 * 
 */
package comp3111.webscraper;


import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.text.Text;

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
    
    @FXML
    private MenuItem menuItemlastSearch;
    
    private String lastSearchedKeyword;
    
    private String currentKeyword;
    
    private int numOfSearch;
    
    
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
    	textAreaConsole.setText("");  
    	numOfSearch=0;
    	
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	List<Item> result = scraper.scrape(textFieldKeyword.getText());
    	String output = "";
    	for (Item item : result) {
    		output += item.getTitle() + "\t" + item.getPrice() + "\t" + item.getUrl() + "\n";
    	}
    	textAreaConsole.setText(output);

        tableController.updateItemList(result);
        
        summary.updateSummary(result,labelCount, labelPrice, labelMin, labelLatest);
        
        updateSearchRecord(textFieldKeyword.getText());
        
              
    }
    
    /**
     * Called when the "LastSearch" button is pressed.
     */
    @FXML
    private void actionLastSearch() {
    	
    	textFieldKeyword.setText(lastSearchedKeyword);
    	actionSearch();
    	menuItemlastSearch.setDisable(true);
    }
    
    /**
     * Called when the "Close" button is pressed.
     */
    @FXML
    private void actionClose() {
    	
    	menuItemlastSearch.setDisable(true);
    	textFieldKeyword.setText("");
    	tableView.getItems().clear();;
    	initialize();
    }
    
    /**
     * Called when the "Quit" button is pressed.
     */
    @FXML
    private void actionQuit() {
    	 
         System.exit(0);
    }
    
    /**
     * Called when the "About Your Team" button is pressed.
     */
    @FXML
    private void actionAboutTeam() {
    	Stage dialog = new Stage();
    	VBox vbox=new VBox(10);
    	vbox.getChildren().add(new Text("1st Team member name: Ng Chi Him\n\t\t    itsc account: chngax\n\t       github account: chihimng\n\n2nd Team member name: Wong Hiu Nam\n\t\t    itsc account: hnwongab\n\t       github account: Camerash\n\n3rd Team member name: Chan Ngo Hin\n\t\t    itsc account: nhchanac\n\t       github account: nhchanac"));
    	Scene scene = new Scene(vbox, 300, 300);
    	dialog.setTitle("About Your Team");
    	dialog.setScene(scene);
    	dialog.show();
    	
    	
    }
    
    /**
     * This update the last and current search record
     * @param current current keyword
     */
    private void updateSearchRecord(String current) {
    	numOfSearch++;
    	lastSearchedKeyword = currentKeyword;
    	currentKeyword = current;
    	
    	
    	
    	if(numOfSearch>=2) {
    		
    		menuItemlastSearch.setDisable(false);
    	}
    }
    
    
}

