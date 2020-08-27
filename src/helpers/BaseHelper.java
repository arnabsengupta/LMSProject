package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public interface BaseHelper {

	
	WebDriver chromedriver = new ChromeDriver();
	
	WebDriverWait wdwait = new WebDriverWait(chromedriver, 1000);
	public static final String URL = "https://alchemy.hguy.co/lms";

	@BeforeTest
	public void openBrowser();
	
	
	@AfterTest
	public void closeBrowser();
}
