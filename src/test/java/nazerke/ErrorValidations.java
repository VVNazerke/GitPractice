package nazerke;

import org.testng.Assert;
import org.testng.annotations.Test;


import nazerke.TestComponents.BaseTest;
import nazerke.TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	
	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginError() {
		lp.login("wohec2334631@tospage.com", "Qwertyasd1!5");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}


}
