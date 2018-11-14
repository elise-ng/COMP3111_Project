package comp3111.webscraper;

public class SearchRecord {
	private String lastSearchedKeyword;
    
    private String currentKeyword;
    
    private int numOfSearch;
    
    private boolean disableLastSearch;
    
    /**
     * default constructor
     */
    public SearchRecord() {
    	numOfSearch=0;
    	disableLastSearch=true;    	
    }
    /**
     * This update the Search Record
     * @param current    The current Keyword searched.
     */
    public void update(String current) {
    	numOfSearch++;
    	lastSearchedKeyword = currentKeyword;
    	currentKeyword = current;
    	if(numOfSearch>=2) {
    		disableLastSearch = false;
    	}
    	else {
    		disableLastSearch = true;
    	}
    	
    }
    /** 
     * @return true if LastSearch should be disabled, false if not
     */
     
    public boolean getDisableLastSearch() {
    	return disableLastSearch;
    }
    
    /** 
     * @return the Last Searched keyword
     */
    public String getLastSearchedKeyword() {
    	return lastSearchedKeyword;
    }
    /**
     * this reset the record.
     */
    public void reset() {
    	lastSearchedKeyword="";        
        currentKeyword="";
    	numOfSearch=0;
    	disableLastSearch=true;  
    }
}
