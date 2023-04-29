package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class GoogleSearchPage extends BasePage {

	public GoogleSearchPage(WebDriver driver) {
		super(driver);
	}

	public void findAndClickElementByHref(String href) {
		$(getElementByHref(href)).fluentWaitElement().click();
	}

	private By getElementByHref(String href) {
		return By.xpath("//a[contains(@href,\"" + href + "\")]");
	}


}
