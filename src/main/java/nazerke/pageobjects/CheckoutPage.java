package nazerke.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nazerke.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="[placeholder='Select Country']")
	WebElement select;
	
	By results = By.cssSelector(".ta-results");
	
	//driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	//driver.findElement(By.cssSelector(".btnn"))
	@FindBy(css=".btnn")
	WebElement submitOrder;
	
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(select, country).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		WebElement e = submitOrder;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click(0);", e);
		ConfirmationPage cfp = new ConfirmationPage(driver);
		return cfp;
	}
	


}
