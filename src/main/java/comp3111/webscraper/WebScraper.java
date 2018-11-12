package comp3111.webscraper;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.util.stream.Collectors;


/**
 * Implements a web scraper that extract item listing data from Craigslist and DCFever.
 * After it is constructed, you can call the method scrape with a keyword, the client will query for
 * related secondhand listings on the sites and return a list of Item objects.
 */
public class WebScraper {

	private static final String CRAIGSLIST_URL = "https://newyork.craigslist.org";
	private static final String DCFEVER_URL = "https://www.dcfever.com/trading";
	private WebClient client;
	private SimpleDateFormat craigslist_dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat dcfever_dateFormat = new SimpleDateFormat("dd/MM HH:mm");

	/**
	 * Default Constructor 
	 */
	public WebScraper() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
	}

    /**
     * Parse HTML of Craigslist result page into list of Item objects
     *
     * @param page - HTML page of search result
     * @return List of Items found
     * @throws Exception if exception occurred
     */
	private List<Item> parseCraigslistItems(HtmlPage page) throws Exception {
        List<Item> result = new ArrayList<>();
        try {
            List<HtmlElement> items = page.getByXPath("//li[@class='result-row']");
            for (int i = 0; i < items.size(); i++) {
                HtmlElement htmlItem = (HtmlElement) items.get(i);
                HtmlAnchor itemAnchor = ((HtmlAnchor) htmlItem.getFirstByXPath(".//p[@class='result-info']/a"));
                HtmlElement spanPrice = ((HtmlElement) htmlItem.getFirstByXPath(".//a/span[@class='result-price']"));
                HtmlTime postedDate = htmlItem.getFirstByXPath(".//p/time[@class='result-date']");

                // It is possible that an item doesn't have any price, we set the price to 0.0
                // in this case
                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                Item item = new Item();
                item.setTitle(itemAnchor.asText());
                item.setUrl(itemAnchor.getHrefAttribute());

                item.setPrice(new Double(itemPrice.replace("$", "")) * 8);

                item.setPostedDate(craigslist_dateFormat.parse(postedDate.getAttribute("datetime")));

                item.setSourcePortal(Item.Portal.CRAIGSLIST);

                result.add(item);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * Parse HTML of DCFever result page into list of Item objects
     *
     * @param page - HTML page of search result
     * @return List of Items found
     * @throws Exception if exception occurred
     */
    private List<Item> parseDcfeverItems(HtmlPage page) throws Exception {
        List<Item> result = new ArrayList<>();
	    try {
            List<HtmlElement> items = page.getByXPath("//*[@id=\"main_wide_column2\"]/table/tbody/tr");
            for (int i = 0; i < items.size(); i++) {
                HtmlElement htmlItem = (HtmlElement) items.get(i);
                HtmlAnchor titleAnchor = htmlItem.getFirstByXPath(".//td[3]/a");
                HtmlTableDataCell priceTD = htmlItem.getFirstByXPath(".//td[@class=\"tlist_price\"]");
                HtmlTableDataCell dateTD = htmlItem.getFirstByXPath(".//td[6]");

                if (priceTD == null) continue; // skip ads

                Item item = new Item();
                item.setTitle(titleAnchor.getTextContent());
                item.setUrl(DCFEVER_URL + '/' + titleAnchor.getHrefAttribute());
                item.setPrice(new Double(priceTD.getTextContent().replace("HK$", "").replace(",", "").replace("--", "0")));

                item.setPostedDate(dcfever_dateFormat.parse(dateTD.getTextContent()));

                item.setSourcePortal(Item.Portal.DCFEVER);

                result.add(item);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

	/**
	 * Scrape web content from Craigslist and DCFever. All available pages will be downloaded and parsed.
	 *
	 * @param keyword - the keyword for searching
	 * @return A list of Item that was found. An empty size list is returned if nothing is found. Null if any exception occurred (e.g. no connectivity)
	 */
	public List<Item> scrape(String keyword) {

		List<Item> result = new ArrayList<>();

		// Craigslist
		try {
		    int pageCount = 0;
			String searchUrl = "/search/sss?sort=rel&query=" + URLEncoder.encode(keyword, "UTF-8");
			while (!searchUrl.isEmpty()) {
                pageCount += 1;
                System.out.println("Loading craigslist page "+ pageCount);
                HtmlPage page = client.getPage(CRAIGSLIST_URL + searchUrl);
                result.addAll(parseCraigslistItems(page));
                HtmlAnchor nextpageAnchor = page.getFirstByXPath("//span[@class='buttons']/a[@title='next page']");
                searchUrl = nextpageAnchor == null ? "" : nextpageAnchor.getHrefAttribute();
            }
            client.close();
		} catch (Exception e) {
            e.printStackTrace();
            return null;
		}

		// DCFEVER
		try {
		    int pageCount = 0;
			String searchUrl = DCFEVER_URL + "/search.php?token=comp3111&type=sell&keyword=" + URLEncoder.encode(keyword, "UTF-8");
			// first page
            System.out.println("Loading dcfever page 1");
            HtmlPage page = client.getPage(searchUrl);
            result.addAll(parseDcfeverItems(page));
            // get last page number
			List<HtmlElement> pageAnchors = page.getByXPath("//div[@class='pagination']/a");
            String lastPageNumStr = pageAnchors.stream().map(HtmlElement::getTextContent).filter(elem -> elem.contains("...")).findFirst().orElse("");
            if (!lastPageNumStr.isEmpty()) {
                int lastPageNum = Integer.parseInt(lastPageNumStr.replace("...", ""));
                for (int i = 2; i <= lastPageNum; ++i) {
                    System.out.println("Loading dcfever page " + i + " of " + lastPageNum);
                    page = client.getPage(searchUrl + "&page=" + i);
                    result.addAll(parseDcfeverItems(page));
                }
            }
            client.close();
		} catch (Exception e) {
            e.printStackTrace();
            return null;
		}

		result.sort(new Item.ItemComparator());
		return result;
	}

}
