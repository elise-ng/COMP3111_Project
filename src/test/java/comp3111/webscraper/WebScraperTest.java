package comp3111.webscraper;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class WebScraperTest {
    @Test
    public void testReturnCraigslistItems() {
        List<Item> searchResult = new WebScraper().scrape("iPhone");
        Assert.assertNotNull(searchResult);
        Assert.assertFalse(searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.CRAIGSLIST)).collect(Collectors.toList()).isEmpty());
    }

    @Test
    public void testReturnDcfeverItems() {
        List<Item> searchResult = new WebScraper().scrape("iPhone");
        Assert.assertNotNull(searchResult);
        Assert.assertFalse(searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.DCFEVER)).collect(Collectors.toList()).isEmpty());
    }

    @Test
    public void testSorting() {
        List<Item> searchResult = new WebScraper().scrape("iPhone");
        for (int i = 1; i < searchResult.size(); ++i) {
            Item thisItem = searchResult.get(i);
            Item prevItem = searchResult.get(i - 1);

            // Check if sorted by price
            Assert.assertFalse(thisItem.getPrice() < prevItem.getPrice());

            // Check if craigslist go first if price equal
            if (thisItem.getPrice() == prevItem.getPrice() && thisItem.getSourcePortal() == Item.Portal.CRAIGSLIST) {
                Assert.assertFalse(prevItem.getSourcePortal() == Item.Portal.DCFEVER);
            }
        }
    }

    @Test
    public void testReturnEmpty() {
        List<Item> searchResult = new WebScraper().scrape("COMP3111IsAnAwesomeCourse");
        Assert.assertTrue(searchResult.isEmpty());
    }
}
