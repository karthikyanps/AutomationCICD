package learningAutomation.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import learningAutomation.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		String productName = "IPHONE 13 PRO";
		
		driver.findElement(By.id("userEmail")).sendKeys("karthikyanps@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Nitin@19");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//css selector - .ta-item:nth-of-type(2)
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btnn.action__submit.ng-star-inserted")));
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results button"));
//		List<WebElement> country = countryList.stream().filter(s->s.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
		
	
	}

}
