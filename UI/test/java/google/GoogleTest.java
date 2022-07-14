package google;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.google.GoogleSearchPage;
import pages.google.GoogleWelcomePage;
import utils.BaseTest;

public class GoogleTest extends BaseTest {

	@Parameters({"search request", "href attribute"})
	@Test(testName = "Поиск в Google и клик по элементу содержащему ссылку")
	public void searchGoogleTest(String searchRequest, String hrefAttribute) {
		open(GOOGLE_URL);
		new GoogleWelcomePage(driver)
				.setSearchRequest(searchRequest)
				.pressEnter();
		new GoogleSearchPage(driver)
				.findAndClickElementByHref(hrefAttribute);
		waitAndCheckCurrentURL(hrefAttribute);
	}


}
