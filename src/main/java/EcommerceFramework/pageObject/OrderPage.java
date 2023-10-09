package EcommerceFramework.pageObject;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceFramework.AbstractComponents.AbstractMethods;

public class OrderPage extends AbstractMethods {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
    	PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[2]")
	List <WebElement> orderName;
   public boolean orderCheck(String orderName) {
	   return this.orderName.stream().anyMatch(s->s.getText().equalsIgnoreCase(orderName));
	   
   }


}
