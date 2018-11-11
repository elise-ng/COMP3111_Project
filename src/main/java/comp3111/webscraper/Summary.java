package comp3111.webscraper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * Summary find the data needed for Summary tab and update the Summary tab
 * 
 *
 */
public class Summary {
	
	private int count; 
    
	private int nonZeroCount;
	
    private double averagePrice; 

    private double minText; 
  
    private Date latestText; 
    
    private String minUrl ;
    
    private String latestUrl ;
	
    
    /**
     * This update the Summary tab if there is no item scrapped.
     * 
     * @param labelCount
     * @param labelPrice
     * @param labelMin
     * @param labelLatest
     */
	void resultNotFound(Label labelCount,Label labelPrice, Hyperlink labelMin, Hyperlink labelLatest) {
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
	 * 
	 * @param result the searched items
	 * @param labelCount
	 * @param labelPrice
	 * @param labelMin
	 * @param labelLatest
	 */
	void updateSummary(List<Item> result,Label labelCount,Label labelPrice, Hyperlink labelMin, Hyperlink labelLatest) {
		count = 0;
		nonZeroCount = 0;
		averagePrice =0;
		minText=0;
		
		minUrl ="";
		latestUrl="";
		
		for (Item item : result) {
    		
    	    			
    			if(item.getPrice() != 0) {
    				averagePrice+=item.getPrice();
    				
    				
    				if(nonZeroCount== 0) {
    				minText = item.getPrice();
    				minUrl =item.getUrl();
    				}
    				else if(item.getPrice()<minText){
    					minText = item.getPrice();
    					minUrl =item.getUrl();
    				}
    				nonZeroCount++;
    				
    			}    				
    				
    			if(count == 0)
    			{
    				latestText=item.getPostedDate();
    				latestUrl=item.getUrl();
    			}
    			else if (item.getPostedDate().getTime()>latestText.getTime())
    			{
    				latestText=item.getPostedDate();
    				latestUrl=item.getUrl();
    			}
    					
    					
    			
    			
    			
    			
    			count++;
        }
    		
    	
		
		
		
			resultNotFound(labelCount,labelPrice, labelMin,labelLatest);
			
		
		if(count !=0){
			averagePrice=averagePrice/nonZeroCount;
			
			labelCount.setText(Integer.toString(count));
			
			
			if(nonZeroCount!= 0)  {
			labelMin.setText(Double.toString(minText));
			labelPrice.setText(Double.toString(averagePrice));
			
			
			labelMin.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	Utils.openURL(minUrl);
			    }
			});
			}
			
			labelLatest.setText(latestText.toString()); 
			
			
			labelLatest.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	Utils.openURL(latestUrl);
			    }
			});
		}
		
		
	
	}
	
	
	
	
	
	

	
	
	
}
