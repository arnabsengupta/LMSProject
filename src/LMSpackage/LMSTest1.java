package LMSpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import helpers.BaseHelper;

public class LMSTest1 implements BaseHelper{

	String toMatch;
	
	//Locators
	public static final String LOC_WEBSITEHEADING = "h1.uagb-ifb-title";
	public static final String LOC_FIRSTINFOBOXTITLE = "#uagb-infobox-f08ebab0-fbf1-40ec-9b2a-c9feeb3d4810 > div > div > div > div.uagb-ifb-title-wrap > h3";
	public static final String LOC_TITLESECONDMOSTPOPULARCOURSE = "div[class^='ld_course_grid']:nth-child(2) div.caption h3.entry-title";
	

	@BeforeTest
	public void openBrowser() {
		chromedriver.navigate().to(URL);

	}


	//1. Verify the website title
	//Goal : Read the title of the website and verify the text
	@Test
	public void verifyWebsiteTitle() {
		toMatch = "Alchemy LMS – An LMS Application";

		System.out.println(chromedriver.getTitle());

		//Read the title of the website and verify the text
		String title = chromedriver.getTitle();
		Boolean result = title.equals(toMatch)?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) ,"website title "+title+" matches the given string");
	}

	//2. Verify the website heading
	//Goal: Read the heading of the website and verify the text
	@Test
	public void verifyWebsiteHeading() {

		toMatch = "Learn from Industry Experts";
		System.out.println(chromedriver.findElement(By.cssSelector(LOC_WEBSITEHEADING)).getText());
		
		//Read the heading of the website and verify the text
		String websiteHeading = chromedriver.findElement(By.cssSelector(LOC_WEBSITEHEADING)).getText();
		Boolean result = websiteHeading.equals(toMatch)?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) , "Website heading "+websiteHeading+" matches the give string");
		
		chromedriver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}

	//3. Verify the title of the first info box
	//Goal: Read the title of the first info box on the website and verify the text
	@Test
	public void verifyTitleFirstInfoBox() {
		toMatch = "Actionable Training";

		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LOC_FIRSTINFOBOXTITLE)));
		
		System.out.println(chromedriver.findElement(By.cssSelector(LOC_FIRSTINFOBOXTITLE)));
		
		String infoBoxTitle = chromedriver.findElement(By.cssSelector(LOC_FIRSTINFOBOXTITLE)).getText();
		
		//Read the title of the first info box on the website and verify the text
		Boolean result = infoBoxTitle.equals(toMatch)?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) , "Title of the first info box "+infoBoxTitle+" matches the given string");
	}

	//4. Verify the title of the second most popular course
	//Goal: Read the title of the second most popular course on the website and verify the text
	@Test
	public void verifyTitleSecondMostPopularCourse() {
		toMatch = "Email Marketing Strategies";
		
		System.out.println("verifyTitleSecondMostPopularCourse: " +chromedriver.findElement(By.cssSelector(LOC_TITLESECONDMOSTPOPULARCOURSE)).getText());
		String titleSecondMostPopularCourse = chromedriver.findElement(By.cssSelector(LOC_TITLESECONDMOSTPOPULARCOURSE)).getText();
		
		//Read the title of the second most popular course on the website and verify the text
		Boolean result = titleSecondMostPopularCourse.equals(toMatch)?true:false;
		System.out.println(result);
		Assert.assertTrue((result == true) , "Title of the second most popular course "+titleSecondMostPopularCourse+" matches with given string");
	}

	@AfterTest
	public void closeBrowser() {
		chromedriver.close();

	}



}
