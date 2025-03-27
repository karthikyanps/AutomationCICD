package learningAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail =  driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='toast-error']")
	WebElement errorMessage;
	
	public ProductCatalog LoginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}

}
