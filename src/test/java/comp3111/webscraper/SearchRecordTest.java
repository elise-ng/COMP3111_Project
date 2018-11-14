package comp3111.webscraper;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Date;
public class SearchRecordTest {
	@Test
	public void recordTest(){
		SearchRecord record = new SearchRecord();
		record.update("1");
		record.update("2");
		Assert.assertEquals(false, record.getDisableLastSearch());
		
	}

}
