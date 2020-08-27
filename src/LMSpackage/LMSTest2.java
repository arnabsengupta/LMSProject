package LMSpackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import helpers.BaseHelper;

public class LMSTest2 implements BaseHelper{

	String URL = "https://alchemy.hguy.co/lms";
	String toMatch;
	

	@BeforeTest
	public void openBrowser() {
		chromedriver.get(URL);
	}


	//5.Navigate to another page
	//Goal: Navigate to the “My Account” page on the site
	@Test(dependsOnMethods = {"verifyLoggedIn"})
	public void navigateToMyAccountAndVerifyPage() {
		toMatch = "My Account";
		chromedriver.findElement(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(5)")).click();
		System.out.println(chromedriver.getTitle());
		String result = chromedriver.getTitle().contains(toMatch)?"matches pass5":"does not match!";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass5");

	}

	//6.Logging into the site 
	//Goal: Open the website and log-in using the provided credentials.
	@Test
	public void verifyLoggedIn() {
		chromedriver.findElement(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(5)")).click();
		chromedriver.findElement(By.cssSelector("a[href='#login']")).click();
		chromedriver.findElement(By.cssSelector("input#user_login")).sendKeys("root");
		chromedriver.findElement(By.cssSelector("input#user_pass")).sendKeys("pa$$w0rd");
		chromedriver.findElement(By.cssSelector("input#wp-submit")).click();
		String result = chromedriver.findElement(By.cssSelector("a.ld-profile-edit-link")).isDisplayed()?"matches pass6":"does not match!";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass6");

	}

	//7.Count the number of courses 
	//Goal: Navigate to the “All Courses” page and count the number of courses.
	@Test
	public void navigateAndverifyNumberOfCourses() {
		chromedriver.findElement(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(2)")).click();
		//Dimension getNumCourses = chromedriver.findElement(By.cssSelector("div[class='ld-course-list-items row'] div[class^='ld_course_grid col-sm-8 col-md-4']")).getSize();
		List<WebElement> list = chromedriver.findElements(By.cssSelector("div[class='ld-course-list-items row'] div[class^='ld_course_grid col-sm-8 col-md-4']"));


		//Dimension getNumCourses = chromedriver.findElement(By.cssSelector("div[class^='ld_course_grid col-sm-8 col-md-4']")).getSize();
		Integer getNumCourses = list.size();
		//String x = getNumCourses.toString();
		System.out.println("Number of courses : "+getNumCourses);
	}

	//8.Contact the admin 
	//Goal: Navigate to the “Contact Us” page and complete the form.
    @Test (dependsOnMethods = {"navigateToCourseAndMarkComplete"})
	public void navigateToContactUsAndCompleteForm() {
		chromedriver.findElement(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(4)")).click();
		chromedriver.findElement(By.cssSelector("#wpforms-8-field_0")).sendKeys("testfullname");
		chromedriver.findElement(By.cssSelector("#wpforms-8-field_1")).sendKeys("test@test.com");
		chromedriver.findElement(By.cssSelector("#wpforms-8-field_3")).sendKeys("testsubject");
		chromedriver.findElement(By.cssSelector("#wpforms-8-field_2")).sendKeys("testmessage");
		chromedriver.findElement(By.cssSelector("#wpforms-submit-8")).click();
		System.out.println("message complete form : " +chromedriver.findElement(By.cssSelector("#wpforms-confirmation-8")).getText());
		wdwait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#wpforms-confirmation-8"), "Thanks for contacting us! We will be in touch with you shortly."));
		String result = chromedriver.findElement(By.cssSelector("#wpforms-confirmation-8")).isDisplayed()?"matches pass8":"does not match!";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass8");
	}
    
    //9. Complete a simple lesson 
    //Goal: Navigate to a particular lesson and complete it.
    @Test (dependsOnMethods = {"navigateToMyAccountAndVerifyPage"})
    public void navigateToCourseAndMarkComplete() {
    	wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(2)")));
    	chromedriver.findElement(By.cssSelector("ul#primary-menu li[id^='menu-item']:nth-child(2)")).click();
    	wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class^='ld-course-list-items row'] > div[class^='ld_course_grid col-sm-8 col-md-4 ']:nth-child(2) > #post-71 > div.ld_course_grid_price")));
    	System.out.println("status : "+chromedriver.findElement(By.cssSelector("div[class^='ld-course-list-items row'] > div[class^='ld_course_grid col-sm-8 col-md-4 ']:nth-child(2) > #post-71 > div.ld_course_grid_price")).getText());
    	String result = chromedriver.findElement(By.cssSelector("div[class^='ld-course-list-items row'] > div[class^='ld_course_grid col-sm-8 col-md-4 ']:nth-child(2) > #post-71 > div.ld_course_grid_price")).getText().equals("Completed")?"matches pass9":"Does not match";
    	System.out.println(result);
    	Assert.assertTrue(result=="matches pass9");
    }

	@AfterTest
	public void closeBrowser() {
		chromedriver.close();
	}

}
