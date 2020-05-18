package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObj.FileTxt;
import pageObj.Home;

public class HomeTest {
	WebDriver driver;

	public String url = "https://sandbox.2checkout.com/sandbox";
	By errorMsg = By.xpath("//div[@id='login-error']");

	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test(priority = 1)
	public void validUserLogIn() {
		Home login = new Home(driver, FileTxt.readXPaths());
		login.username("tmidlane13");
		login.password("VnkbGoV5");
		login.logInBtn();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://sandbox.2checkout.com/sandbox/home/dashboard";

		Assert.assertEquals(actualUrl, expectedUrl);
		wait(1000);
	}

	@Test(priority = 2)
	public void falseLogIn() {
		Home login = new Home(driver, FileTxt.readXPaths());
		login.logOut();
		login.username("lamadrama");
		login.password("dramalama77876");
		login.logInBtn();

		WebElement error = driver.findElement(errorMsg);
		Assert.assertTrue(error.isDisplayed());
		wait(1000);
	}

	public static void wait(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
