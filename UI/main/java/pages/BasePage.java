package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {

	protected WebDriver driver;

	public BasePage(WebDriver driver){
		this.driver = driver;
	}

	public WebElement fluentWaitElement(final By locator) {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10L))
				.pollingEvery(Duration.ofSeconds(1L))
				.ignoring(NoSuchElementException.class);
		return wait.until(driver1 -> driver1.findElement(locator));
	}

	public boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitAndCheckCurrentURL(String expectedURL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
		wait.until(driver1 -> driver1.getCurrentUrl().equals(expectedURL));
	}


}
