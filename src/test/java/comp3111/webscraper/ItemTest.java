package comp3111.webscraper;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void testComparatorSortByPrice() {
		Item item1 = new Item();
		item1.setPrice(1000);
		Item item2 = new Item();
		item2.setPrice(1001);

		List<Item> actual = Arrays.asList(item2, item1);
		actual.sort(new Item.ItemComparator());

		List<Item> expected = Arrays.asList(item1, item2);

		assertEquals(expected, actual);
	}

	@Test
	public void testComparatorSortBySourcePortal() {
		Item item1 = new Item();
		item1.setPrice(1000);
		item1.setSourcePortal(Item.Portal.CRAIGSLIST);
		Item item2 = new Item();
		item2.setPrice(1000);
		item2.setSourcePortal(Item.Portal.DCFEVER);

		List<Item> actual = Arrays.asList(item2, item1);
		actual.sort(new Item.ItemComparator());

		List<Item> expected = Arrays.asList(item1, item2);

		assertEquals(expected, actual);
	}
}
