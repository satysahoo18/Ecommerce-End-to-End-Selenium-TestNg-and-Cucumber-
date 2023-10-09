package EcommerceFramework.pageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceFramework.AbstractComponents.AbstractMethods;

public class AddToCartPage extends AbstractMethods {
	WebDriver driver;
    public AddToCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    @FindBy(css=".cartWrap")
    List<WebElement> cart;
    
    @FindBy(css=".subtotal button")
    WebElement submit;
    
    public boolean productVerification( String product) {
    	return cart.stream().anyMatch(s->s.findElement(By.tagName("h3")).getText().equalsIgnoreCase(product));
    	
    }
    
    public PaymentPage checkOut() {
    	submit.click();
    	PaymentPage paymentpage = new PaymentPage(driver);
    	return paymentpage;
    }
}
