package learningAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import learningAutomation.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmationMessage()
	{
		return confirmMessage.getText();
	}
}
