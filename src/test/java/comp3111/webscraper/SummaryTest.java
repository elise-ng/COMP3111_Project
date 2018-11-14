package comp3111.webscraper;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Date;

public class SummaryTest  {

	@Test
	public void testUpdate() {
		
		Date date1= new Date(1);
		Date date2= new Date(2);
		Date date3= new Date(3);
		
		Item item1 = new Item();
		item1.setPrice(10);
		item1.setPostedDate(date1);
		
		Item item2 = new Item();
		item2.setPrice(0);
		item2.setPostedDate(date2);
		
		Item item3 = new Item();
		item3.setPrice(5);
		item3.setPostedDate(date3);
		
		List<Item> result1 = new ArrayList<>();
		result1.add(item1);
		result1.add(item2);
		result1.add(item3);
		
		Summary summaryTest= new Summary();
		
		summaryTest.updateSummary(result1);
		
		 Assert.assertEquals(3, summaryTest.getCount());
		
		 
		 List<Item> result2 = new ArrayList<>();
		 summaryTest.updateSummary(result2);
		 Assert.assertEquals(0, summaryTest.getCount());
		 
		 List<Item> result3 = new ArrayList<>();
		 result3.add(item2);
		 summaryTest.updateSummary(result3);
		 Assert.assertEquals(0, summaryTest.getNonZeroCount());
		 
		
	}
}
