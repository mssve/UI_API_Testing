package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.BasePage;
import java.time.Duration;

public class BaseTest {

	protected final String GOOGLE_URL = "https://google.com";
	protected final String PERFORMANCE_LAB_URL = "https://www.performance-lab.ru/website-testing/";
	protected WebDriver driver;
	protected BasePage basePage;

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		driver = WebDriverManager.chromedriver().create();
		configureDriver();
		basePage = new BasePage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		driver.quit();
	}

	protected void configureDriver() {
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	protected void open(String url) {
		driver.get(url);
	}

	public void waitAndCheckCurrentURL(String expectedURL) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
		wait.until(driver1 -> driver1.getCurrentUrl().equals(expectedURL));
	}


}
