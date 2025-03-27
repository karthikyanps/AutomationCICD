package learningAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import learningAutomation.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[normalize-space()='Place Order']")
	private WebElement submit; //strictly accessible only within the class as it is marked as private & only class methods can access this variable 
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
		
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	private WebElement selectCountry;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}

}
