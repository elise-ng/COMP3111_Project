package comp3111.webscraper;

import java.util.Comparator;
import java.util.Date;

public class Item {

	/**
	 * Custom Comparator for Item class
	 */
    public static class ItemComparator implements Comparator<Item> {
		/**
		 * Implementation of compare method of Comparator interface
		 * @param item1 - first item to be compared
		 * @param item2 - second item to be compared
		 * @return integer between -1 and 1, -1 denotes first < second, 0 denotes first = second, 1 denotes first > second
		 */
        public int compare(Item item1, Item item2) {
            int comparePrice = Double.compare(item1.getPrice(), item2.getPrice());
            return comparePrice != 0 ? comparePrice : item1.getSourcePortal().compareTo(item2.getSourcePortal());
        }
    }

	/**
	 * Enum representing Selling Portal
	 */
	public enum Portal {
		CRAIGSLIST,
		DCFEVER;
	}

	private String title ; 
	private double price ;
	private String url ;
	private Date postedDate;
	private Portal sourcePortal;

	/**
	 * Getter of title property
	 * @return Title of the item
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter of title property
	 * @param title - New title of the item
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter of price property
	 * @return Price of the item
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter of price property
	 * @param price - New price of the item
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Getter of url property
	 * @return Url of the item
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter of url property
	 * @param url - New url of the item
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter of posted date property
	 * @return Posted date of the item
	 */
	public Date getPostedDate() { return postedDate; }

	/**
	 * Setter of posted date property
	 * @param postedDate - New posted date of the item
	 */
	public void setPostedDate(Date postedDate) { this.postedDate = postedDate; }

	/**
	 * Getter of source portal property
	 * @return Source portal of the item
	 */
	public Portal getSourcePortal() { return sourcePortal; }

	/**
	 * Setter of source portal property
	 * @param sourcePortal - New source portal of the item
	 */
	public void setSourcePortal(Portal sourcePortal) { this.sourcePortal = sourcePortal; }
}
