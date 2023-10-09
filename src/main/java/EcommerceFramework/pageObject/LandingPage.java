package EcommerceFramework.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceFramework.AbstractComponents.AbstractMethods;

public class LandingPage extends AbstractMethods{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);		

	}
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement element;
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement element1;
	
	
	By locator = By.id("toast-container");
	
	By locator1 = By.cssSelector(".mb-3");
	
     public List<WebElement> getProductList() {
    	 elementVisibility(locator1);
    	 return products;
     }
	
	 public void addProductToCart(String productName) {
		 WebElement product = getProductList().stream().filter(s->s.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
         .findFirst().orElse(null); 
		product.findElement(By.className("fa-shopping-cart")).click();
	
		
	 }
	 
	
}
