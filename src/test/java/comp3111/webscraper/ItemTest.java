package comp3111.webscraper;


import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ItemTest {
	@Test
	public void testSetTitle() {
		Item i = new Item();
		i.setTitle("ABCDE");
		Assert.assertEquals(i.getTitle(), "ABCDE");
	}

	@Test
	public void testSetPrice() {
		Item i = new Item();
		i.setPrice(12.34);
		Assert.assertEquals(Double.compare(i.getPrice(), 12.34), 0);
	}

	@Test
	public void testSetUrl() {
		Item i = new Item();
		i.setUrl("https://example.com");
		Assert.assertEquals(i.getUrl(), "https://example.com");
	}

	@Test
	public void testSetPostedDate() {
		Item i = new Item();
		Date date = new Date();
		i.setPostedDate(date);
		Assert.assertEquals(i.getPostedDate(), date);
	}

	@Test
	public void testSetSourcePortal() {
		Item i = new Item();
		i.setSourcePortal(Item.Portal.DCFEVER);
		Assert.assertEquals(i.getSourcePortal(), Item.Portal.DCFEVER);
	}

}
