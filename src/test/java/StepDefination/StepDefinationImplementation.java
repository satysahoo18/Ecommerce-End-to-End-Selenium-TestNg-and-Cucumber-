package StepDefination;
import java.io.IOException;
import org.testng.Assert;
import EcommerceFramework.TestComponents.BaseTest;
import EcommerceFramework.pageObject.AddToCartPage;
import EcommerceFramework.pageObject.LandingPage;
import EcommerceFramework.pageObject.Login;
import EcommerceFramework.pageObject.PaymentPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImplementation extends BaseTest{
	public Login login;
	public LandingPage landingPage;
	public AddToCartPage cart;
	public PaymentPage paymentPage ;
	@Given ("Land on login Page")
	public void land_on_login_page() throws IOException {
		login = launchApplication();
	}
	
	@Given ("^Logged in using username (.+) and password (.+)$")
	public void loggen_in_using_username_and_password(String username, String password) {
		landingPage = login.executeLogin(username,password);
	}
	
	@When ("^Add the product (.+) to the cart$")
	public void add_the_product_to_cart(String product) {
		landingPage.addProductToCart(product);
	}
	@And ("^Checkout product (.+) and submit the order$")
	public void checkout_product_and_submit_Order(String product) throws InterruptedException{
		cart = landingPage.clickOnCart();
		Assert.assertTrue(cart.productVerification(product));
		paymentPage = cart.checkOut();
		paymentPage.selectCountry("India");
		paymentPage.submit();
		
	}
	
	 @Then ("{string} success message is displayed after on confirmation page")
	 public void success_message_is_displayed(String msg) {
		 Assert.assertEquals(paymentPage.successMessage(), msg); 
		 closeBrowser();
	 }
	 
	 @Then ("^\"([^\"]*)\" error message is displayed$")
	 public void error_message_is_displayed(String msg) {
		 String result = login.getErrorMessage();
		 Assert.assertEquals(result, msg);
		 closeBrowser();
	 }
	
}
