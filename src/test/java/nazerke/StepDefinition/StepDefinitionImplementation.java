package nazerke.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nazerke.TestComponents.BaseTest;
import nazerke.pageobjects.CartPage;
import nazerke.pageobjects.CheckoutPage;
import nazerke.pageobjects.ConfirmationPage;
import nazerke.pageobjects.LandingPage;
import nazerke.pageobjects.ProductCatalogue;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage lp;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage cfp;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		lp = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = lp.login(username, password);
	}
	
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String productName) {
		CartPage cp = productCatalogue.goToCartPage();
		boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage chp = cp.goToCheckout();
		chp.selectCountry("india");
		cfp = chp.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmationpage(String string) {
		Assert.assertTrue(cfp.getConfirmationPage().equalsIgnoreCase(string), "Passed! Good job!");
		driver.close();
	}
	
}
