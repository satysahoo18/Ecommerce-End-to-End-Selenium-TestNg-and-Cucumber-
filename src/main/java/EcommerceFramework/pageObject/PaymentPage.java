package EcommerceFramework.pageObject;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import EcommerceFramework.AbstractComponents.AbstractMethods;

public class PaymentPage extends AbstractMethods{
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css="[placeholder = 'Select Country']")
	WebElement countryOption;
	
	@FindBy(css=".list-group-item span")
	List<WebElement> options;
	
    By element = By.cssSelector(".list-group");
    
    @FindBy(css=".action__submit")
    WebElement submitBtn;
    
    @FindBy(tagName="h1")
    WebElement message;
    
    
    public void selectCountry(String country) {
    	a.sendKeys(countryOption, country).build().perform();
    	elementVisibility(element);
//    	options.stream().filter(s->s.getText().equalsIgnoreCase("India")).forEach(s->s.click());
    	
//    	options.stream().map(s->s.getText()).forEach(s->System.out.println(s));
    	
//    	List <WebElement> option = options.stream().filter(s->s.getText().equalsIgnoreCase("India")).collect(Collectors.toList());
//    	option.get(0).click();
    	for(int i=0;i<options.size();i++) {
    		if(options.get(i).getText().equalsIgnoreCase(country)) {
    			options.get(i).click();
    		}
    	}
    }
    public void submit() {
    	/*JavascriptExecutor js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click", submitBtn);*/
    	
//    		Point elementLocation = submitBtn.getLocation();
//    		a.moveToLocation(1017, 319).click().build().perform();
    	    elementClickable(submitBtn);
    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",submitBtn);
//    		submitBtn.click();
    		
    }
    
    public String successMessage() {
    	return message.getText();
    }
}
