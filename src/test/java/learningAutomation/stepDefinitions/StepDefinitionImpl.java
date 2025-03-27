package learningAutomation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learningAutomation.TestComponents.BaseTest;
import learningAutomation.pageobjects.CartPage;
import learningAutomation.pageobjects.CheckOutPage;
import learningAutomation.pageobjects.ConfirmationPage;
import learningAutomation.pageobjects.LandingPage;
import learningAutomation.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalog = landingPage.LoginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_the_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalog.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		confirmationPage = checkoutPage.submitOrder();
	}
	
	//Then "THANKYOU FOR THE ORDER." message is displayed on confimation page
	@Then("{string} message is displayed on confimation page")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then ("^\"([^\"]*)\" message is displayed$")
	public void some_error_message_is_displayed(String string1)
	{
		Assert.assertEquals(string1, landingPage.getErrorMessage());
		driver.close();
	}
	
	
}
