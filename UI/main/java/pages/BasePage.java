package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class BasePage {

	protected WebDriver driver;
	private By element;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public BasePage $(By by) {
		element = by;
		return this;
	}

	public WebElement fluentWaitElement() {
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10L))
				.pollingEvery(Duration.ofSeconds(1L))
				.ignoring(NoSuchElementException.class);
		return wait.until(driver1 -> driver1.findElement(element));
	}

	public boolean isElementPresent() {
		try {
			fluentWaitElement();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}


}
