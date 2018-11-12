package comp3111.webscraper;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class ItemComparatorTest {
    @Test
    public void testSortByPrice() {
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
    public void testSortBySourcePortal() {
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
