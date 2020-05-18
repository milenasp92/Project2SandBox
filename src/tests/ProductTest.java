package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObj.FileTxt;
import pageObj.Home;
import pageObj.Products;
import pageObj.Registration;

public class ProductTest {
	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	
	public String url = "https://sandbox.2checkout.com/sandbox";
	By successful = By.xpath("//span[@class='form_valid large']");
	private static final String PeakyBPrice = "//*[@id=\"update_bulk\"]/table/tbody/tr[2]/td[6]/input";
	private static final String KilligEvePrice = "//*[@id=\"update_bulk\"]/table/tbody/tr[3]/td[6]/input";
	private static final String NickCavePrice = "//*[@id=\"update_bulk\"]/table/tbody/tr[4]/td[6]/input";
	private static final String AirAlbumPrice = "//*[@id=\"update_bulk\"]/table/tbody/tr[5]/td[6]/input";
	private static final String KingsOfCPrice = "//*[@id=\"update_bulk\"]/table/tbody/tr[6]/td[6]/input";
	private static final String products = "products";
	
	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void logIn() {
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Home home = new Home(driver, FileTxt.readXPaths());
		home.username("tmidlane13");
		home.password("VnkbGoV5");
		home.logInBtn();
	}


	@Test(priority = 2)
	public void GoToCreateProductPage() {
		Products products = new Products(driver, FileTxt.readXPaths());
		products.createProductPage();

		Assert.assertEquals(driver.getCurrentUrl(), "https://sandbox.2checkout.com/sandbox/products/create_product");
	}

	@Test(priority = 3)
	public void testCreateProduct() throws InterruptedException {

		for (int i = 1; i <= 5; i++) {
			
			Products products = new Products(driver, FileTxt.readXPaths());
			products.createProductPage();
			products.createProduct(driver, i);
			
			WebElement successfulAdd = driver.findElement(successful);
			sa.assertTrue(successfulAdd.isDisplayed());
			sa.assertAll();
			
			products.addNewProduct();
		}
	}
	
	@Test(priority = 4)
	public void updateProductsPrices() {
		Products products = new Products(driver, FileTxt.readXPaths());
		
		products.createProductPage();
		products.editProductPage();
		
		products.PeakyPrice(driver);
		sa.assertEquals(driver.findElement(By.xpath(PeakyBPrice)).getAttribute("value"), "1095.4");
		
		products.KillinEvePrice(driver);
		sa.assertEquals(driver.findElement(By.xpath(KilligEvePrice)).getAttribute("value"), "1120.4");
		
		products.NickCavePrice(driver);
		sa.assertEquals(driver.findElement(By.xpath(NickCavePrice)).getAttribute("value"), "845.6");
		
		products.AirPrice(driver);
		sa.assertEquals(driver.findElement(By.xpath(AirAlbumPrice)).getAttribute("value"), "848.4");
		
		products.KingsOfPrice(driver);
		sa.assertEquals(driver.findElement(By.xpath(KingsOfCPrice)).getAttribute("value"), "979.6");
		
		sa.assertAll();
	}

	@Test(priority = 5)
	public void LogOut () {
		Home login = new Home(driver, FileTxt.readXPaths());
		login.logOut();
	}
	
	@AfterClass
	public void closeDriver() {
		driver.quit();
	}

}
