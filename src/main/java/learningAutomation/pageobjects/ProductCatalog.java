package learningAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import learningAutomation.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;

	@FindBy(css=".ng-animating")
	WebElement spinner;
	
//	List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	By productsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	//Action method to get the products list
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisppear(spinner);
	}
	
	public List<WebElement> getCartDetails()
	{
		return cartItems;
	}

}
