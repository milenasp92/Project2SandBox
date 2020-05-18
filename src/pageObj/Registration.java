package pageObj;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Registration {

	WebDriver driver;
	Map<String, String> xPaths;
	
	private static final String account = "account";
	private static final String userT = "userT";
	private static final String createUsername = "createUsername";
	private static final String emailAdd = "emailAdd";
	private static final String usernameAdd = "usernameAdd";
	private static final String passwordAdd = "passwordAdd";
	private static final String confirmAdd = "confirmAdd";
	private static final String createBtn = "createBtn";
	private static final String avatar = "avatar";
	private static final String logOut = "logOut";
	private static final String logIn = "logIn";
	
	
	public Registration (WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}
		
	public void register(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(account))).click();
		driver.findElement(By.xpath(xPaths.get(userT))).click();
		driver.findElement(By.xpath(xPaths.get(createUsername))).click();
		
	}
	
	public void email (String data) {
		driver.findElement(By.xpath(xPaths.get(emailAdd))).sendKeys(data);
	}
	
	public void username (String data) {
		driver.findElement(By.xpath(xPaths.get(usernameAdd))).sendKeys(data);
	}
	
	public void password (String data) {
		driver.findElement(By.xpath(xPaths.get(passwordAdd))).sendKeys(data);
	}
	
	public void confirmPassword (String data) {
		driver.findElement(By.xpath(xPaths.get(confirmAdd))).sendKeys(data);
	}
	
	public void selectAccess () {
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
	}
	
	public void createUserBtn () {
		driver.findElement(By.xpath(xPaths.get(createBtn))).click();
	}
	
	public void registerNext () {
		
		driver.findElement(By.xpath(xPaths.get(account))).click();
		driver.findElement(By.xpath(xPaths.get(userT))).click();
		driver.findElement(By.xpath(xPaths.get(createUsername))).click();
	}
	
	public static String ListOfUsers(int i, int j) {
		FileInputStream fis;
		XSSFWorkbook wb;
		try {
			fis = new FileInputStream("ListOfUsers.xlsx");
			wb = new XSSFWorkbook(fis);
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed";
		}
	}
	
	public String Reg30Users(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(xPaths.get(account))).click();
		driver.findElement(By.xpath(xPaths.get(userT))).click();
		driver.findElement(By.xpath(xPaths.get(createUsername))).click();
		
		Thread.sleep(1000);

		driver.findElement(By.xpath(xPaths.get(usernameAdd))).sendKeys(ListOfUsers(i, 1));
		driver.findElement(By.xpath(xPaths.get(emailAdd))).sendKeys(ListOfUsers(i, 2));
		driver.findElement(By.xpath(xPaths.get(passwordAdd))).sendKeys(ListOfUsers(i, 3));
		driver.findElement(By.xpath(xPaths.get(confirmAdd))).sendKeys(ListOfUsers(i, 4));
		driver.findElement(By.id("sa-access")).click();
		driver.findElement(By.id("va_user1")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(xPaths.get(createBtn))).click();
		
		return driver.getTitle();

	}
	
	public void logOut() {
		driver.findElement(By.xpath(xPaths.get(avatar))).click();
		driver.findElement(By.xpath(xPaths.get(logOut))).click();
	}
}
