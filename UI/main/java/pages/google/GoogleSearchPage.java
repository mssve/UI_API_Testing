package pages.google;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;


public class GoogleSearchPage extends BasePage {

	By responseElement = By.cssSelector(".yuRUbf > a");

	public GoogleSearchPage(WebDriver driver) {
		super(driver);
	}

	public void findAndClickElementByHref(String href) {
		fluentWaitElement(By.xpath("//a[contains(@href,\"" + href + "\")]")).click();
	}









}
