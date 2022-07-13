import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.performance_lab.PerformanceLabTestingPage;
import utils.BaseTest;

public class PerformanceLabTest extends BaseTest {

	final String URL = "https://www.performance-lab.ru/website-testing/";

	@Parameters ({"button color", "button color on hover"})
	@Test(testName = "Проверка изменения цвета кнопки 'Узнать цены'")
	public void checkButtonOnHover(String buttonColor, String buttonColorOnHover) throws InterruptedException {
		driver.get(URL);

		new PerformanceLabTestingPage(driver)
				.checkFindOutPricesButtonColor(buttonColor)
				.hoverFindOutPricesElement()
				.checkFindOutPricesButtonColor(buttonColorOnHover)
				.hoverTitle()
				.checkFindOutPricesButtonColor(buttonColor);
	}








}
