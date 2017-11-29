

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AjaxCalendarDemoPage {

	WebElement calendarContainer;

	public AjaxCalendarDemoPage(final WebElement calendarContainer) {
		this.calendarContainer = calendarContainer;
	}

	static final String[] MONTHS = new String[] { 
			"Jan", "Feb", "Mar", "Apr", "May", 
			"Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" 
			};

	public void setDate(final int dd, final int mm, final int yyyy) {
		// Open months
		clickCalendarTitle();
		// Open Year range
		clickCalendarTitle();

		// is year present on calendar? no then navigate in loop;
		while (true) {

			final int no = checkYear(yyyy);
			if (no == 0) {
				break;
			} else if (no == -1) {
				goLeft();
			} else if (no == 1) {
				goRight();
			} else {
				System.out.println("Invalid option.");
			}
		}

		// select year
		clickElementByText("" + yyyy);

		// select month
		// convert int onth in MMM format
		final String monthToSelect = MONTHS[mm - 1];
		clickElementByText(monthToSelect);

		// select date
		selectDateByText(dd);

	}

	private void goRight() {
		calendarContainer.findElement(By.className("ajax__calendar_next")).click();
		wait(1);
	}

	private void goLeft() {
		calendarContainer.findElement(By.className("ajax__calendar_prev")).click();
		wait(1);

	}

	private void selectDateByText(final int dd) {
		// select date excluding from other month.
		// Select all TD tags which are not having class as ajax__calendar_other
		final List<WebElement> allDates = calendarContainer.findElements(
				By.cssSelector("#Content_DemoContent_defaultCalendarExtender_daysBody "+
						"td:not(.ajax__calendar_other)"));
		allDates.get(dd - 1).click();
		wait(2);
	}

	private void clickElementByText(final String txt) {
		calendarContainer.findElement(By.xpath("//*[text()='" + txt + "']")).click();
		wait(2);

	}

	private void clickCalendarTitle() {
		calendarContainer.findElement(By.className("ajax__calendar_title")).click();
		wait(2);
	}

	private void wait(final int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (final Exception e) {
			// TODO: handle exception
		}
	}

	int checkYear(final int yyyy) {

		final String title = calendarContainer.findElement(
				By.className("ajax__calendar_title")).getText();
		final int min = Integer.parseInt(title.split("-")[0]);
		final int max = Integer.parseInt(title.split("-")[1]);
		// yyyy is less than range return -1
		if (yyyy < min) {
			return -1;
		}
		/// yyyy is greater than range retrun 1
		if (yyyy > max) {
			return 1;
		}
		/// yyyy in with in range return 0
		return 0;
	}

}
