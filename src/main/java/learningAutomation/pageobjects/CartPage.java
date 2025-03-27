package learningAutomation.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import learningAutomation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;

	public boolean verifyProductDisplay(String productName) {

		boolean match = cartProduct.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
