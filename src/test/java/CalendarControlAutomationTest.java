

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CalendarControlAutomationTest{

	
	@Test
	public void testHomePage(){
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		final WebDriver driver = new ChromeDriver();
		driver.get("https://ajaxcontroltoolkit.devexpress.com/Calendar/Calendar.aspx");
		//Open calendar by clicking to date text box
		driver.findElement(By.id("Content_DemoContent_Date1")).click();
		// Locate the calendar control box container
		final WebElement calendarContainer = driver
				.findElement(By.className("ajax__calendar_container"));
		final AjaxCalendarDemoPage calendar = new AjaxCalendarDemoPage(calendarContainer);
		calendar.setDate(21, 4, 2050);
		
	}
}
