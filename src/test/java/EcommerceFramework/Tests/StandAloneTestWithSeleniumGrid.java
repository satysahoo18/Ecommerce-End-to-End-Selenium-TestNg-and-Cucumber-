package EcommerceFramework.Tests;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import EcommerceFramework.pageObject.AddToCartPage;
import EcommerceFramework.pageObject.LandingPage;
import EcommerceFramework.pageObject.Login;
import EcommerceFramework.pageObject.PaymentPage;

public class StandAloneTestWithSeleniumGrid {

	public static void main(String[] args) throws InterruptedException, MalformedURLException  {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.43.7:4444"),cap);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Login login = new Login(driver);
		login.goTo("https://rahulshettyacademy.com/client");
		LandingPage landingPage = login.executeLogin("satyabratasahoo981@gmail.com","S.saty@6991");
		landingPage.addProductToCart("Zara Coat 3");
		AddToCartPage cart = landingPage.clickOnCart();
		Assert.assertTrue(cart.productVerification("Zara coat 3"));
		PaymentPage paymentPage = cart.checkOut();
		paymentPage.selectCountry("India");
		paymentPage.submit();
		Assert.assertEquals(paymentPage.successMessage(), "THANKYOU FOR THE ORDER.");
		
		
	}
}
