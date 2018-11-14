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
 * This update the Summary data
 * @param result the searched items
 */
    public void updateSummary(List<Item> result) {
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
    		   	
		
		
		
		if(count !=0){
			
					
			
			if(nonZeroCount!= 0)  {
			averagePrice=averagePrice/nonZeroCount;
			
		}
		}
		
		
	
	}
		
	/** 
	 * @return no. of item counted
	 */
		
	public int getCount() {
		return count;
	}
	
	/**
	 * 
	 * @return no. of item with non-zero price
	 */
	public int getNonZeroCount() {
		return nonZeroCount;
	}
	/**
	 * 
	 * @return average price of all items
	 */
	public double getAveragePrice() {
		return averagePrice;
	}
	
	/**
	 * 
	 * @return the min price of all items
	 */
	public double getMinText() {
		return minText;
	}

	/**
	 * 
	 * @return the date of the latest item
	 */
	public Date getLatestText() {
		return latestText;
	}
	/**
	 * 
	 * @return the Url of the item with min price
	 */
	public String getMinUrl() {
		return minUrl;
	}
    /**
     * 
     * @return the Url of the latest item
     */
	public String getLatestUrl() {
		return latestUrl;
	}
 	
	
	
}
