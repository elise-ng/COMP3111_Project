package comp3111.webscraper;

import java.util.Comparator;
import java.util.Date;

public class Item {

    public static class ItemComparator implements Comparator<Item> {
        public int compare(Item item1, Item item2) {
            int comparePrice = Double.compare(item1.getPrice(), item2.getPrice());
            return comparePrice != 0 ? comparePrice : item1.getSourcePortal().compareTo(item2.getSourcePortal());
        }
    }

	public enum Portal {
		CRAIGSLIST,
		DCFEVER;
	}

	private String title ; 
	private double price ;
	private String url ;
	private Date postedDate;
	private Portal sourcePortal;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPostedDate() { return postedDate; }
	public void setPostedDate(Date postedDate) { this.postedDate = postedDate; }
	public Portal getSourcePortal() { return sourcePortal; }
	public void setSourcePortal(Portal sourcePortal) { this.sourcePortal = sourcePortal; }
}
