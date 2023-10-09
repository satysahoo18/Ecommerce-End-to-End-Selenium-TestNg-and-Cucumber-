package EcommerceFramework.AbstractComponents;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import EcommerceFramework.pageObject.AddToCartPage;
import EcommerceFramework.pageObject.OrderPage;

public class AbstractMethods
{
	WebDriver driver;
	protected WebDriverWait wait;
	protected Actions a;
	public AbstractMethods(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		a = new Actions(driver);
	}


	
	
	public void elementVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void elementInvisibility(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void elementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	@FindBy (css="[routerlink = '/dashboard/myorders']")
	WebElement orderBtn;
	public OrderPage clickOnOrder() {
		orderBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	By toast = By.id("toast-container");
	
	public AddToCartPage clickOnCart() throws InterruptedException {
		elementVisibility(toast);
		elementInvisibility(animation);
//		Thread.sleep(5000);
		elementClickable(cartButton);
		cartButton.click();
	    AddToCartPage addToCart = new AddToCartPage(driver);
	    return addToCart;
 }
	
	
}
