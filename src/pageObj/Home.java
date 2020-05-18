package pageObj;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	WebDriver driver;
	Map<String, String> xPaths;
	private static final String username = "username";
	private static final String password= "password";
	private static final String logIn = "logIn";
	private static final String avatar = "avatar";
	private static final String logOut = "logOut";


	public Home (WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}

	public void username(String data) {
		driver.findElement(By.xpath(xPaths.get(username))).sendKeys(data);
	}

	public void password(String data) {
		driver.findElement(By.xpath(xPaths.get(password))).sendKeys(data);
	}

	public void logInBtn() {
		driver.findElement(By.xpath(xPaths.get(logIn))).click();
	}

	public void logOut() {
		driver.findElement(By.xpath(xPaths.get(avatar))).click();
		driver.findElement(By.xpath(xPaths.get(logOut))).click();
	}

}
