package performance_lab;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.performance_lab.PerformanceLabTestingPage;
import utils.BaseTest;

public class PerformanceLabTest extends BaseTest {

	@Parameters({"button color blue", "button color black"})
	@Test(testName = "Проверка изменения цвета кнопки 'Узнать цены'")
	public void checkButtonOnHover(String buttonColorBlue, String buttonColorBlack) throws InterruptedException {
		open(PERFORMANCE_LAB_URL);
		new PerformanceLabTestingPage(driver)
				.checkFindOutPricesButtonColor(buttonColorBlue)
				.hoverFindOutPricesElement()
				.checkFindOutPricesButtonColor(buttonColorBlack)
				.hoverTitle()
				.checkFindOutPricesButtonColor(buttonColorBlue);
	}


}
