package EcommerceFramework.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceFramework.AbstractComponents.AbstractMethods;

public class Login  extends AbstractMethods
{
	WebDriver driver;
    public Login(WebDriver driver) {
    	
    	super(driver);
    	this.driver=driver;
    	PageFactory.initElements(driver, this);
    	
	}

    @FindBy(id="userEmail")
    WebElement userEmail;
    
    @FindBy(id="userPassword")
    WebElement password;
    
    @FindBy(id="login")
    WebElement login;
    public void goTo(String url) {
    	driver.get(url);
    }
    
    @FindBy(css=".ng-trigger-flyInOut")
    WebElement loginErrorToast;
   
//    public String executeLoginError(String email, String pass) {
//    	userEmail.sendKeys(email);
//		password.sendKeys(pass);
//        login.click();
//        //ng-tns-c4-34 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
//        elementVisibility(By.cssSelector(".ng-trigger-flyInOut"));
//        return loginErrorToast.getText();
//    }
    public String getErrorMessage() {
    	
        elementVisibility(By.cssSelector(".ng-trigger-flyInOut"));
        return loginErrorToast.getText();
    }
  
    
    
    public LandingPage executeLogin(String email, String pass) {
    	userEmail.sendKeys(email);
		password.sendKeys(pass);
        login.click();
        LandingPage landingpage = new LandingPage(driver);
        return landingpage;
    }
    
}
