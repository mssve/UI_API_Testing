import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.google.GoogleSearchPage;
import pages.google.GoogleWelcomePage;
import utils.BaseTest;

public class GoogleTest extends BaseTest {

	@Parameters ({"search request", "href attribute"})
	@Test(testName = "Поиск в Google и клик по элементу содержащему ссылку"
	, priority = 1)
	public void searchGoogleTest(String searchRequest, String hrefAttribute) {
		driver.get("https://google.com");
		new GoogleWelcomePage(driver)
			.setSearchRequest(searchRequest)
			.pressEnter();

		new GoogleSearchPage(driver)
				.findAndClickElementByHref(hrefAttribute);

		new BasePage(driver).waitAndCheckCurrentURL(hrefAttribute);
	}









}
