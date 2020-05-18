package pageObj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Products {
	WebDriver driver;
	Map<String, String> xPaths;

	public String name, shortDescription, longDescription, tangible, recurring, approvedUrl;
	public int Id;
	public double price;
	private static final String username = "username";
	private static final String password = "password";
	private static final String logIn = "logIn";
	private static final String product = "product";
	private static final String productAdd = "productAdd";
	private static final String productName = "productName";
	private static final String productId = "productId";
	private static final String productSdescr = "productSdescr";
	private static final String productLdescr = "productLdescr";
	private static final String productPrice = "productPrice";
	private static final String productTangibleNo = "productTangibleNo";
	private static final String productRecc = "productRecc";
	private static final String productSave = "productSave";
	private static final String productView = "productView";
	private static final String productEdit = "productEdit";
	private static final String PeakyBPrice = "PeakyBPrice";
	private static final String KillingEvePrice = "KillingEvePrice";
	private static final String NickCavePrice = "NickCavePrice";
	private static final String AirAlbumPrice = "AirAlbumPrice";
	private static final String KingsPrice = "KingsPrice";
	private static final String SaveChanges = "SaveChanges";

	public Products(String name, int Id, String shortDescription, String longDescription, double price,
			String tangible, String recurring, String approvedUrl) {

		this.name = name;
		this.Id = Id;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.price = price;
		this.tangible = tangible;
		this.recurring = recurring;
		this.approvedUrl = approvedUrl;
	}

	public Products(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}

	public void createProductPage() {
		driver.findElement(By.xpath(xPaths.get(product))).click();
		driver.findElement(By.xpath(xPaths.get(productAdd))).click();
	}

	public void editProductPage() {
		driver.findElement(By.xpath(xPaths.get(product))).click();
		driver.findElement(By.xpath(xPaths.get(productEdit))).click();

	}

	public static String productsData(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("Products.xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Error";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	public void createProduct(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(xPaths.get(productName))).sendKeys(productsData(i, 0));
		driver.findElement(By.xpath(xPaths.get(productId))).sendKeys(productsData(i, 1));
		driver.findElement(By.xpath(xPaths.get(productSdescr))).sendKeys(productsData(i, 2));
		driver.findElement(By.xpath(xPaths.get(productLdescr))).sendKeys(productsData(i, 3));
		driver.findElement(By.xpath(xPaths.get(productPrice))).sendKeys(productsData(i, 4));
		driver.findElement(By.xpath(xPaths.get(productTangibleNo))).click();
		driver.findElement(By.xpath(xPaths.get(productRecc))).click();
		driver.findElement(By.xpath(xPaths.get(productSave))).click();

		Thread.sleep(1000);

	}

	public void addNewProduct() {
		driver.findElement(By.xpath(xPaths.get(product))).click();
		driver.findElement(By.xpath(xPaths.get(productAdd))).click();
	}

	public void PeakyPrice(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(product))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String peaky = driver.findElement(By.xpath(xPaths.get(PeakyBPrice))).getAttribute("value");
		double peakyPrice = Double.parseDouble(peaky);
		double peakyNewPrice = peakyPrice + 100;
		String peakyUpdated = String.valueOf(peakyNewPrice);

		System.out.println(xPaths.get(PeakyBPrice));
		WebElement lustUpdatedPrice = driver.findElement(By.xpath(xPaths.get(PeakyBPrice)));
		lustUpdatedPrice.clear();
		lustUpdatedPrice.sendKeys(peakyUpdated);

		driver.findElement(By.xpath(xPaths.get(SaveChanges))).click();
	}

	public void KillinEvePrice(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(product))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String eve = driver.findElement(By.xpath(xPaths.get(KillingEvePrice))).getAttribute("value");
		double evePrice = Double.parseDouble(eve);
		double eveNewPrice = evePrice + 100;
		String eveUpdated = String.valueOf(eveNewPrice);

		WebElement agonyUpdatedPrice = driver.findElement(By.xpath(xPaths.get(KillingEvePrice)));
		agonyUpdatedPrice.clear();
		agonyUpdatedPrice.sendKeys(eveUpdated);

		driver.findElement(By.xpath(xPaths.get(SaveChanges))).click();
	}

	public void NickCavePrice(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(product))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String nick = driver.findElement(By.xpath(xPaths.get(NickCavePrice))).getAttribute("value");
		double nickPrice = Double.parseDouble(nick);
		double nickNewPrice = nickPrice + 100;
		String nickUpdated = String.valueOf(nickNewPrice);

		WebElement originUpdatedPrice = driver.findElement(By.xpath(xPaths.get(NickCavePrice)));
		originUpdatedPrice.clear();
		originUpdatedPrice.sendKeys(nickUpdated);

		driver.findElement(By.xpath(xPaths.get(SaveChanges))).click();
	}

	public void AirPrice(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(product))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String air = driver.findElement(By.xpath(xPaths.get(AirAlbumPrice))).getAttribute("value");
		double airPrice = Double.parseDouble(air);
		double airNewPrice = airPrice + 100;
		String airUpdated = String.valueOf(airNewPrice);

		WebElement airUpdatedPrice = driver.findElement(By.xpath(xPaths.get(AirAlbumPrice)));
		airUpdatedPrice.clear();
		airUpdatedPrice.sendKeys(airUpdated);

		driver.findElement(By.xpath(xPaths.get(SaveChanges))).click();
	}

	public void KingsOfPrice(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(product))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String kings = driver.findElement(By.xpath(xPaths.get(KingsPrice))).getAttribute("value");
		double kingsPrice = Double.parseDouble(kings);
		double kingsNewPrice = kingsPrice + 100;
		String kingsUpdated = String.valueOf(kingsNewPrice);

		WebElement ladyUpdatedPrice = driver.findElement(By.xpath(xPaths.get(KingsPrice)));
		ladyUpdatedPrice.clear();
		ladyUpdatedPrice.sendKeys(kingsUpdated);

		driver.findElement(By.xpath(xPaths.get(SaveChanges))).click();
	}
}
