package functionLib;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(functionLib.TestNG_Listener.class)

/*
This Test Case searching with a keyword "Pots" in the Amazon Website and Adding one of the item to Cart.
Validating if item added. Then removing the item from cart and validating if Cart is empty. 

Implemented TestNG: "Assert" and "Listener". 
Both Implicit wait and Explicit wait.
Webelement lists.  

*/
public class ItemAdd {
	static WebDriver  driver = new ChromeDriver();
	@BeforeClass
	public static void LaunchSite() {
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		 WebDriverWait wait = new WebDriverWait(driver,90000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hello, Sign in')]")));

	}

	@Test
	public void addItemToCart() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Pots");
		Thread.sleep(4000);
		act.sendKeys(Keys.ENTER).build().perform();
		//driver.findElement(By.xpath("//span[@id='nav-search-submit-text']")).click();
		 WebDriverWait wait = new WebDriverWait(driver,90000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'results')]")));
		 List<WebElement> SearchElement= driver.findElements(By.xpath("//div[@class='a-section a-spacing-medium']"));
		
		if (SearchElement.size()>1) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'results')]")));
				SearchElement= driver.findElements(By.xpath("//div[@class='a-section a-spacing-medium']"));
				SearchElement.get(1).click();
				driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
				
				Thread.sleep(2000);
				act.sendKeys(Keys.ESCAPE).build().perform();
				driver.findElement(By.xpath("//a[@class='nav-logo-link']")).click();
				String CartValue=driver.findElement(By.xpath("//a[@id='nav-cart']/span[@id='nav-cart-count']")).getText();
				Assert.assertEquals(CartValue.contains("0"), false);

		}
		else {
			System.out.println("No Search result");
			Assert.fail("No Search result");
		}
				
	}
	
	@Test
	public void removeItem() {
		WebDriverWait wait = new WebDriverWait(driver,90000);
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Delete']")));
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		
		driver.findElement(By.xpath("//a[@class='nav-logo-link']")).click();
		String CartValue=driver.findElement(By.xpath("//a[@id='nav-cart']/span[@id='nav-cart-count']")).getText();
		Assert.assertEquals(CartValue.contains("0"), true);
		
	}

	@AfterClass	
	public static void endTest() {	
		driver.close();
	}
	
	
	
	
}
