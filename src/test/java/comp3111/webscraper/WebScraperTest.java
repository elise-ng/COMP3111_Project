package comp3111.webscraper;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class WebScraperTest {
    @Test
    public void testNormalReturn() {
        List<Item> searchResult = new WebScraper().scrape("PS4");

        // check if items empty
        Assert.assertNotNull(searchResult);

        // check if items from craigslist exist
        Assert.assertFalse(searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.CRAIGSLIST)).collect(Collectors.toList()).isEmpty());

        // check if items from dcfever exist
        Assert.assertFalse(searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.DCFEVER)).collect(Collectors.toList()).isEmpty());

        // check sorting
        for (int i = 1; i < searchResult.size(); ++i) {
            Item thisItem = searchResult.get(i);
            Item prevItem = searchResult.get(i - 1);

            // check if sorted by price
            Assert.assertFalse(thisItem.getPrice() < prevItem.getPrice());

            // check if craigslist go first if price equal
            if (thisItem.getPrice() == prevItem.getPrice() && thisItem.getSourcePortal() == Item.Portal.CRAIGSLIST) {
                Assert.assertFalse(prevItem.getSourcePortal() == Item.Portal.DCFEVER);
            }
        }
    }

    @Test
    public void testEmptyReturn() {
        List<Item> searchResult = new WebScraper().scrape("COMP3111IsAnAwesomeCourse");
        Assert.assertTrue(searchResult.isEmpty());
    }
}
