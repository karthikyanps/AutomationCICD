package learningAutomation.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import learningAutomation.TestComponents.BaseTest;
import learningAutomation.TestComponents.Retry;
import learningAutomation.pageobjects.CartPage;
import learningAutomation.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
		//String productName = "IPHONE 13 PRO";
		landingPage.LoginApplication("karthikeyanps@gmail.com", "Nitin@19");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "IPHONE 13 PRO";
		ProductCatalog productCatalog = landingPage.LoginApplication("karthikyanps11@gmail.com", "Nitin@19");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();

		boolean match = cartPage.verifyProductDisplay("IPHONE 14 PRO");
		Assert.assertFalse(match);
	}

}
