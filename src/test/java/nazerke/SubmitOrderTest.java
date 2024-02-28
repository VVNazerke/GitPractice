package nazerke;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nazerke.TestComponents.BaseTest;
import nazerke.pageobjects.CartPage;
import nazerke.pageobjects.CheckoutPage;
import nazerke.pageobjects.ConfirmationPage;
import nazerke.pageobjects.OrderPage;
import nazerke.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData")
	// public void submitOrder(String email, String password, String productName)
	// throws IOException, InterruptedException {

	public void submitOrder(HashMap<String, String> map)
			throws IOException, InterruptedException {

		String countryName = "India";
		ProductCatalogue productCatalogue = lp.login(map.get("email"), map.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(map.get("productName"));
		CartPage cp = productCatalogue.goToCartPage();
		boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage chp = cp.goToCheckout();
		chp.selectCountry(countryName);
		ConfirmationPage cfp = chp.submitOrder();

		Assert.assertTrue(cfp.getConfirmationPage().equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Passed! Good job!");

	}

	// To verify that Adidas is diplaying in orders' page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = lp.login("wohec23631@tospage.com", "Qwertyasd1!");
		OrderPage op = productCatalogue.goToOrderPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));
	}

//	@DataProvider
//	public Object[][] getData(){
//		return new Object [][] {{"wohec23631@tospage.com", "Qwertyasd1!", "ADIDAS ORIGINAL"}};
//	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "wohec23631@tospage.com");
//		map.put("password", "Qwertyasd1!");
//		map.put("productName", "ADIDAS ORIGINAL");
//
//		return new Object[][] { { map } };
//	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\nazerke\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
	


}
