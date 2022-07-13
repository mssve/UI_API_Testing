package pages.performance_lab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import pages.BasePage;

public class PerformanceLabTestingPage extends BasePage {

	By findOutPricesButton = By.xpath("//span[contains(text(), 'цены')]/parent::span/parent::a");
	By pageTitle = By.xpath("//h1");

	public PerformanceLabTestingPage(WebDriver driver) {
		super(driver);
	}

	public PerformanceLabTestingPage checkFindOutPricesButtonColor(String expectedColor) throws InterruptedException {
		Thread.sleep(1000L);
		String colorValue = fluentWaitElement(findOutPricesButton).getCssValue("background-color");
		String hexColor = Color.fromString(colorValue).asHex();
		Assert.assertEquals(hexColor, expectedColor);
		return this;
	}

	public PerformanceLabTestingPage hoverFindOutPricesElement() {
		WebElement element = fluentWaitElement(findOutPricesButton);
		new Actions(driver).moveToElement(element).perform();
		return this;
	}

	public PerformanceLabTestingPage hoverTitle() {
		WebElement element = fluentWaitElement(pageTitle);
		new Actions(driver).moveToElement(element).perform();
		return this;
	}






}
