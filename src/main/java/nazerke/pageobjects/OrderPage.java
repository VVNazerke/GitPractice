package nazerke.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nazerke.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody tr td:nth-child(3)")
	List<WebElement> orderTable;
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = orderTable.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;
	}

}
