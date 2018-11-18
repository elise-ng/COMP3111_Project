/**
 * 
 */
package comp3111.webscraper;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
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
    
    private SearchRecord searchRecord;

    private List<Item> result = new ArrayList<>();

	/**
     * Default controller
     */
    public Controller() {
    	scraper = new WebScraper();
    	tableController = new TableController();
    	summary = new Summary();
    	searchRecord = new SearchRecord();  
    }

    /**
     * Default initializer. It is empty.
     */
    @FXML
    private void initialize() {    	
    	tableController.initialize(tableView);
    	resultNotFound();
    	textAreaConsole.setText("");  
    	    	
    }
    
    /**
     * Called when the search button is pressed.
     */
    @FXML
    private void actionSearch() {
    	System.out.println("actionSearch: " + textFieldKeyword.getText());
    	result = scraper.scrape(textFieldKeyword.getText());
    	outputResult();
        updateSearchRecord(textFieldKeyword.getText());
    }
    
    /**
     * Called when the "LastSearch" button is pressed.
     */
    @FXML
    private void actionLastSearch() {
    	
    	textFieldKeyword.setText(searchRecord.getLastSearchedKeyword());
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
    	tableView.getItems().clear();
		searchRecord.reset();
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
    	
    	
    	
    	searchRecord.update(current);
    		
    		menuItemlastSearch.setDisable(searchRecord.getDisableLastSearch());
    	
    }
    /**
     * This update the Summary tab if there is no item scrapped.
     */
    public void resultNotFound() {
		labelCount.setText("-");
		labelPrice.setText("-");
		labelMin.setText("-");
		labelLatest.setText("-");
		
		labelMin.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    }
		});
		
		labelLatest.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	
		    }
		});
	
	}
    
    
    /**
     * This update the Summary tab
     * @param result the new list of item
     */
    
    public void updateSummaryTab(List<Item> result)
    {
    	summary.updateSummary(result);
    	resultNotFound();
			
		
		if(summary.getCount() !=0){
			
			
			labelCount.setText(Integer.toString(summary.getCount()));
			
			
			if(summary.getNonZeroCount()!= 0)  {
			
			labelMin.setText(Double.toString(summary.getMinText()));
			labelPrice.setText(Double.toString(summary.getAveragePrice()));
			
			
			labelMin.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	Utils.openURL(summary.getMinUrl());
			    }
			});
			}
			
			labelLatest.setText(summary.getLatestText().toString()); 
			
			
			labelLatest.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	Utils.openURL(summary.getLatestUrl());
			    }
			});
		}
    }

    @FXML
    public void refineSearch() {
    	if(result.size() <= 0) {
			textAreaConsole.setText("Please perform a search first before refining");
			return;
		}
		textAreaConsole.setText("");

    	String keyword = textFieldKeyword.getText();
    	List<Item> filteredList = new ArrayList<>();
    	result.forEach(item -> {
    		if(item.getTitle().contains(keyword)) {
    			filteredList.add(item);
			}
		});
    	result = filteredList;

    	outputResult();
	}

	private void outputResult() {
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

		updateSummaryTab(result);
	}
}

