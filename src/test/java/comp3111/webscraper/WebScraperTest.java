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

        // check items from craigslist
        List<Item> craigslistItems = searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.CRAIGSLIST)).collect(Collectors.toList());
        // check if exist
        Assert.assertFalse(craigslistItems.isEmpty());
        // check if more than one page of items from craigslist
        Assert.assertTrue((craigslistItems.size() / 120) > 1); // 120 items per page on craigslist


        // check items from dcfever
        List<Item> dcfeverItems = searchResult.stream().filter(item -> item.getSourcePortal().equals(Item.Portal.DCFEVER)).collect(Collectors.toList());
        // check if exist
        Assert.assertFalse(dcfeverItems.isEmpty());
        // check if more than one page of items from craigslist
        Assert.assertTrue((dcfeverItems.size() / 30) > 1); // 30 items per page on dcfever

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
