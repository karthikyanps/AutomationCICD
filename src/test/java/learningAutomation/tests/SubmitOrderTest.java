package learningAutomation.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import learningAutomation.TestComponents.BaseTest;
import learningAutomation.pageobjects.CartPage;
import learningAutomation.pageobjects.CheckOutPage;
import learningAutomation.pageobjects.ConfirmationPage;
import learningAutomation.pageobjects.LandingPage;
import learningAutomation.pageobjects.OrderPage;
import learningAutomation.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		String productName = input.get("product");//"ZARA COAT 3";

		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

//		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button"));
//		List<WebElement> country = countryList.stream().filter(s->s.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = landingPage.LoginApplication("karthikyanps11@gmail.com", "Nitin@19");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//learningAutomation//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider
//	public Object[][] getData()
//	{

//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "karthikyanps@gmail.com");
//	map.put("password", "Nitin@19");
//	map.put("productName", "IPHONE 13 PRO");
//
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "karthikyanps@gmail.com");
//	map1.put("password", "Nitin@19");
//	map1.put("productName", "IPHONE 13 PRO");

//	return new Object[][] {{map},{map1}};
//		return new Object[][] {{"karthikyanps@gmail.com","Nitin@19","IPHONE 13 PRO"},{"karthikyanps11@gmail.com","Nitin@19","ZARA COAT 3"}};
//	}

}
