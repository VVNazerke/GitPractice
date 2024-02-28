package nazerke.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nazerke.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> cartProductList;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	private List<WebElement> getCartProducts(){
		return cartProductList;
	}
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = getCartProducts().stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
		
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		CheckoutPage chp = new CheckoutPage(driver);
		return chp;
	}

}
