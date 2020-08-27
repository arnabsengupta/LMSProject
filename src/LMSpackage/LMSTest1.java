package LMSpackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import helpers.BaseHelper;

public class LMSTest1 implements BaseHelper{

	//String URL = "https://alchemy.hguy.co/lms";
	String toMatch;

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

		String result = chromedriver.getTitle().equals(toMatch)?"matches pass1":"does not match";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass1");
	}

	//2. Verify the website heading
	//Goal: Read the heading of the website and verify the text
	@Test
	public void verifyWebsiteHeading() {

		toMatch = "Learn from Industry Experts";
		System.out.println(chromedriver.findElement(By.cssSelector("h1.uagb-ifb-title")).getText());
		String result = chromedriver.findElement(By.cssSelector("h1.uagb-ifb-title")).getText().equals(toMatch)?"matches pass2":"Does not match";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass2");
		chromedriver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
	}

	//3. Verify the title of the first info box
	//Goal: Read the title of the first info box on the website and verify the text
	@Test
	public void verifyTitleFirstInfoBox() throws InterruptedException {
		toMatch = "Actionable Training";
		//System.out.println(firefoxdriver.findElement(By.cssSelector("section[class^='wp-block-uagb-columns']:nth-child(2) div[class^='wp-block-uagb-column']:nth-child(1) h3.uagb-ifb-title")));
		chromedriver.manage().window().maximize();
		//verifyWebsiteTitle();
		Thread.sleep(10000);
		System.out.println(chromedriver.findElement(By.cssSelector("#uagb-infobox-f08ebab0-fbf1-40ec-9b2a-c9feeb3d4810 > div > div > div > div.uagb-ifb-title-wrap > h3")));
		String result = chromedriver.findElement(By.cssSelector("#uagb-infobox-f08ebab0-fbf1-40ec-9b2a-c9feeb3d4810 > div > div > div > div.uagb-ifb-title-wrap > h3")).getText().equals(toMatch)?"matches pass3":"does not match!";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass3");
	}

	//4. Verify the title of the second most popular course
	//Goal: Read the title of the second most popular course on the website and verify the text
	@Test
	public void verifyTitleSecondMostPopularCourse() {
		toMatch = "Email Marketing Strategies";
		System.out.println("verifyTitleSecondMostPopularCourse: " +chromedriver.findElement(By.cssSelector("div[class^='ld_course_grid']:nth-child(2) div.caption h3.entry-title")).getText());
		String result = chromedriver.findElement(By.cssSelector("div[class^='ld_course_grid']:nth-child(2) div.caption h3.entry-title")).getText().equals(toMatch)?"matches pass4":"does not match!";
		System.out.println(result);
		Assert.assertTrue(result=="matches pass4");
	}

	@AfterTest
	public void closeBrowser() {
		chromedriver.close();

	}



}
