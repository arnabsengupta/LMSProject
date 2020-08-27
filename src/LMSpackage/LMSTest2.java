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

	String toMatch;

	//Locators
	public static final String LOC_MYACCOUNTTAB = "ul#primary-menu li[id^='menu-item']:nth-child(5)";
	public static final String LOC_LOGINBTN = "a[href='#login']";
	public static final String LOC_USERNAME = "input#user_login";
	public static final String LOC_PASSWORD = "input#user_pass";
	public static final String LOC_SUBMITBTN = "input#wp-submit";
	public static final String LOC_EDITPROFILELINK = "a.ld-profile-edit-link";
	public static final String LOC_ALLCOURSESTAB = "ul#primary-menu li[id^='menu-item']:nth-child(2)";
	public static final String GETNUMROWSLINK = "div[class='ld-course-list-items row'] div[class^='ld_course_grid col-sm-8 col-md-4']";
	public static final String LOC_CONTACTUSTAB = "ul#primary-menu li[id^='menu-item']:nth-child(4)";
	public static final String LOC_FULLNAME = "#wpforms-8-field_0";
	public static final String LOC_EMAIL = "#wpforms-8-field_1";
	public static final String LOC_SUBJECT = "#wpforms-8-field_3";
	public static final String LOC_MESSAGE = "#wpforms-8-field_2";
	public static final String LOC_SENDMESSAGEBTN = "#wpforms-submit-8";
	public static final String LOC_SUBMITMSG = "#wpforms-confirmation-8";
	public static final String LOC_SECONDlESSON = "div[class^='ld-course-list-items row'] > div[class^='ld_course_grid col-sm-8 col-md-4 ']:nth-child(2) > #post-71 > div.ld_course_grid_price";

	@BeforeTest
	public void openBrowser() {
		chromedriver.get(URL);
	}


	//5.Navigate to another page
	//Goal: Navigate to the “My Account” page on the site
	@Test(dependsOnMethods = {"verifyLoggedIn"})
	public void navigateToMyAccountAndVerifyPage() {
		toMatch = "My Account";

		//Navigate to the “My Account” page on the site
		chromedriver.findElement(By.cssSelector(LOC_MYACCOUNTTAB)).click();
		System.out.println(chromedriver.getTitle());
		String pageTitle = chromedriver.getTitle();

		//Read the page title and verify that you are on the correct page.
		Boolean result = pageTitle.contains(toMatch)?true:false;
		System.out.println(result);
		Assert.assertTrue((result==true) ,"page title: "+pageTitle+ " matches with the given string");

	}

	//6.Logging into the site 
	//Goal: Open the website and log-in using the provided credentials.
	@Test
	public void verifyLoggedIn() {
		chromedriver.findElement(By.cssSelector(LOC_MYACCOUNTTAB)).click();

		//Log-in using the provided credentials.
		chromedriver.findElement(By.cssSelector(LOC_LOGINBTN)).click();
		chromedriver.findElement(By.cssSelector(LOC_USERNAME)).sendKeys("root");
		chromedriver.findElement(By.cssSelector(LOC_PASSWORD)).sendKeys("pa$$w0rd");
		chromedriver.findElement(By.cssSelector(LOC_SUBMITBTN)).click();

		//Verify that you have logged in.
		Boolean result = chromedriver.findElement(By.cssSelector(LOC_EDITPROFILELINK)).isDisplayed()?true:false;
		System.out.println(result);
		Assert.assertTrue((result==true) ,"User is successfully logged in");

	}

	//7.Count the number of courses 
	//Goal: Navigate to the “All Courses” page and count the number of courses.
	@Test
	public void navigateAndverifyNumberOfCourses() {

		//Navigate to the “All Courses” page
		chromedriver.findElement(By.cssSelector(LOC_ALLCOURSESTAB)).click();

		//Get the number of rows
		List<WebElement> list = chromedriver.findElements(By.cssSelector(GETNUMROWSLINK));


		//count the number of courses.
		Integer getNumCourses = list.size();


		//Print the number of courses on the page.		
		System.out.println("Number of courses : "+getNumCourses);
	}

	//8.Contact the admin 
	//Goal: Navigate to the “Contact Us” page and complete the form.
	@Test (dependsOnMethods = {"navigateToCourseAndMarkComplete"})
	public void navigateToContactUsAndCompleteForm() {

		//Navigate to the “Contact Us” page
		chromedriver.findElement(By.cssSelector(LOC_CONTACTUSTAB)).click();

		//Complete the form
		chromedriver.findElement(By.cssSelector(LOC_FULLNAME)).sendKeys("testfullname");
		chromedriver.findElement(By.cssSelector(LOC_EMAIL)).sendKeys("test@test.com");
		chromedriver.findElement(By.cssSelector(LOC_SUBJECT)).sendKeys("testsubject");
		chromedriver.findElement(By.cssSelector(LOC_MESSAGE)).sendKeys("testmessage");
		chromedriver.findElement(By.cssSelector(LOC_SENDMESSAGEBTN)).click();

		//Read and print the message displayed after submission
		
		String printmsg = chromedriver.findElement(By.cssSelector(LOC_SUBMITMSG)).getText();
		System.out.println("message complete form : " +printmsg);
		wdwait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(LOC_SUBMITMSG), "Thanks for contacting us! We will be in touch with you shortly."));


		Boolean result = chromedriver.findElement(By.cssSelector(LOC_SUBMITMSG)).isDisplayed()?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) , "Message after successful submission is displayed");
	}

	//9. Complete a simple lesson 
	//Goal: Navigate to a particular lesson and complete it.
	@Test (dependsOnMethods = {"navigateToMyAccountAndVerifyPage"})
	public void navigateToCourseAndMarkComplete() {

		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LOC_ALLCOURSESTAB)));


		//Select the menu item that says “All Courses” and click it.
		chromedriver.findElement(By.cssSelector(LOC_ALLCOURSESTAB)).click();

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LOC_SECONDlESSON)));

		System.out.println("status : "+chromedriver.findElement(By.cssSelector(LOC_SECONDlESSON)).getText());

		//Navigate to a particular lesson
		Boolean result = chromedriver.findElement(By.cssSelector(LOC_SECONDlESSON)).getText().equals("Completed")?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) , "The chosen lesson is completed.No action required");
	}

	@AfterTest
	public void closeBrowser() {
		chromedriver.quit();
	}

}
