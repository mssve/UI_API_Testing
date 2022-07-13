package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class GoogleWelcomePage extends BasePage {

	By searchInput = By.cssSelector("[class = 'gLFyf gsfi']");


	public GoogleWelcomePage(WebDriver driver) {
		super(driver);
	}

	public GoogleWelcomePage setSearchRequest(String request) {
		fluentWaitElement(searchInput).sendKeys(request);
		return this;
	}

	public void pressEnter() {
		fluentWaitElement(searchInput).sendKeys(Keys.ENTER);
	}








}
