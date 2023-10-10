package EcommerceFramework.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceFramework.TestComponents.BaseTest;
import EcommerceFramework.TestComponents.Retry;
import EcommerceFramework.pageObject.AddToCartPage;
import EcommerceFramework.pageObject.LandingPage;
import EcommerceFramework.pageObject.Login;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;


public class ErrorValidationTest extends BaseTest {
	
@Test
public void gitSatyaY(){
	System.out.println("This is DevelopY");
	System.out.println("This is Developx");
	} 
	
@Test(groups = {"Smoke"}, retryAnalyzer=Retry.class)
public void loginErrorValidation() throws IOException {
	
	login.executeLogin("satybrathsahoo981@gmail.com","S.saty@6991");
	String result = login.getErrorMessage();
	Assert.assertEquals(result, "Incorrect email or password.");
}

@Test
public void CartProductValidation() throws InterruptedException {
	
	LandingPage landingPage = login.executeLogin("satybrathsahoo@gmail.com","S.saty@6991");
	landingPage.addProductToCart("Zara Coat 3");
	AddToCartPage cart = landingPage.clickOnCart();
	Assert.assertFalse(cart.productVerification("zara coat 33"));
}



}
