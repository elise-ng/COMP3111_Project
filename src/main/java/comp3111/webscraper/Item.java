package comp3111.webscraper;


import javax.sound.sampled.Port;
import java.util.Date;

public class Item {

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
