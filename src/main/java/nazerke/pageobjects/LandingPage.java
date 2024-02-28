package nazerke.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nazerke.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page Factory
	
//	WebElement email = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement email;
	
	//driver.findElement(By.id("userPassword")).sendKeys("Qwertyasd1!");
	@FindBy(id="userPassword")
	WebElement password;
	
	//driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue login(String userEmail, String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		loginButton.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
}
