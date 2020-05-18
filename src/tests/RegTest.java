package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObj.FileTxt;
import pageObj.Home;
import pageObj.Registration;

public class RegTest {
	WebDriver driver;
	WebDriverWait wait;
	SoftAssert sa = new SoftAssert();

	public String url = "https://sandbox.2checkout.com/sandbox";
	public static final String usernameMsg = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[2]/span";
	public static final String emailMsg = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[1]/span";
	public static final String passMsg = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[3]/span";
	public static final String passConfMsg = "//*[@id=\"create_username\"]/div[1]/div[1]/div/div[4]/span";

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void login() {
		driver.navigate().to(url);
		Home login = new Home(driver, FileTxt.readXPaths());
		Registration registration = new Registration(driver, FileTxt.readXPaths());

		login.username("tmidlane13");
		login.password("VnkbGoV5");
		login.logInBtn();
		wait(1000);
		

	}

	@Test(priority = 1)
	public void registrationPage() {
		driver.navigate().to(url);

		Registration registration = new Registration(driver, FileTxt.readXPaths());

		registration.register(driver);
		Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/acct/create_username");
	}

	@Test(priority = 2)
	public void validUser() throws InterruptedException {
		Registration registration = new Registration(driver, FileTxt.readXPaths());
		registration.register(driver);
		registration.email("lamadrama@gmail.com");
		registration.username("lamainproblama");
		registration.password("lamaproblama123");
		registration.confirmPassword("lamaproblama123");
		registration.selectAccess();
		registration.createUserBtn();

		Assert.assertEquals("Update User", driver.getTitle());
	}

	@Test(priority = 3)
	public void RegisterWithoutUsername() throws InterruptedException {
		Registration registration = new Registration(driver, FileTxt.readXPaths());
		registration.register(driver);
		registration.email("dramalama@gmail.com");
		registration.password("inlamaproblama");
		registration.confirmPassword("inlamaproblama");
		registration.selectAccess();
		registration.createUserBtn();

		WebElement usernameError = driver.findElement(By.xpath(usernameMsg));
		Assert.assertTrue(usernameError.isDisplayed());
	}

	@Test(priority = 4)
	public void RegistraterWithoutEmail() throws InterruptedException {
		Registration registration = new Registration(driver, FileTxt.readXPaths());
		registration.register(driver);
		registration.username("probablylama");
		registration.password("drama123");
		registration.confirmPassword("drama123");
		registration.selectAccess();
		registration.createUserBtn();

		WebElement emailErrorMessage = driver.findElement(By.xpath(emailMsg));
		Assert.assertTrue(emailErrorMessage.isDisplayed());
	}

	@Test(priority = 5)
	public void RegistrationWithoutPassword() throws InterruptedException {
		Registration registration = new Registration(driver, FileTxt.readXPaths());
		registration.register(driver);
		registration.email("drama@gmail.com");
		registration.username("dramalama9977");
		registration.confirmPassword("dramalama9797");
		registration.selectAccess();
		registration.createUserBtn();

		WebElement passwordErrorMessage = driver.findElement(By.xpath(passMsg));
		Assert.assertTrue(passwordErrorMessage.isDisplayed());
	}

	@Test(priority = 6)
	public void RegistraterWithoutPassConfirm() throws InterruptedException {
		Registration registration = new Registration(driver, FileTxt.readXPaths());
		registration.register(driver);
		registration.email("lama@gmail.com");
		registration.username("drama35464");
		registration.confirmPassword("drama");
		registration.selectAccess();
		registration.createUserBtn();

		WebElement confirmPasswordErrorMessage = driver.findElement(By.xpath(passConfMsg));
		Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed());
	}

	@Test(priority = 7)
	public void Register30Users() throws InterruptedException {

		for (int i = 1; i <= 30; i++) {

			Registration registration = new Registration(driver, FileTxt.readXPaths());
			registration.register(driver);

			String actualTitle = registration.Reg30Users(driver, i);
			sa.assertEquals(actualTitle, "Update User");
			sa.assertAll();

			registration.logOut();

			Home login = new Home(driver, FileTxt.readXPaths());

			login.username("tmidlane13");
			login.password("VnkbGoV5");
			login.logInBtn();
		}
	}

	@AfterClass
	public void close() {
		driver.quit();
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
