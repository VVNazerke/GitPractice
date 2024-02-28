package nazerke;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("wohec23631@tospage.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwertyasd1!");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cart h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']")));
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		WebElement e = driver.findElement(By.cssSelector(".btnn"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", e);

		String conf = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(conf.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Passed! Good job!");
		
		driver.quit();
		
		System.out.println("Practice git");
		System.out.println("Practice git");
		

	}

}
