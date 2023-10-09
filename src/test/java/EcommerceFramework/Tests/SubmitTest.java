package EcommerceFramework.Tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import EcommerceFramework.TestComponents.BaseTest;
import EcommerceFramework.pageObject.AddToCartPage;
import EcommerceFramework.pageObject.LandingPage;
import EcommerceFramework.pageObject.OrderPage;
import EcommerceFramework.pageObject.PaymentPage;

public class SubmitTest extends BaseTest {
	
	@Test(dataProvider = "getData", groups = {"DataTest", "Smoke"})
	public void submitOrderTest(HashMap<String,String> map) throws IOException, InterruptedException  {
	
		LandingPage landingPage = login.executeLogin(map.get("email"),map.get("pass"));
		landingPage.addProductToCart(map.get("product"));
		AddToCartPage cart = landingPage.clickOnCart();
		Assert.assertTrue(cart.productVerification(map.get("product")));
		PaymentPage paymentPage = cart.checkOut();
		paymentPage.selectCountry("India");
		paymentPage.submit();
		Assert.assertEquals(paymentPage.successMessage(), "THANKYOU FOR THE ORDER.");
	}
	
	@Test (dependsOnMethods = {"submitOrderTest"}, groups="DataTest")
	public void orderVerification() {
		LandingPage landingPage = login.executeLogin("satyabratasahoo981@gmail.com","S.saty@6991");
		OrderPage order =landingPage.clickOnOrder();
		Assert.assertTrue(order.orderCheck("zara coat 3")); 
	}
	@DataProvider 
	public Object[][] getData() throws IOException {
//		return new Object[][] {{"satyabratasahoo981@gmail.com","S.saty@6991", "Zara coat 3"},{"satybrathsahoo@gmail.com","S.saty@6991","Adidas Original"}};
		/*HashMap<Object,Object> map = new HashMap<Object,Object>();
		map.put("email","satyabratasahoo981@gmail.com");
		map.put("pass","S.saty@6991");
		map.put("product","Zara coat 3");
		
		HashMap<Object,Object> map1 = new HashMap<Object,Object>();
		map1.put("email","satybrathsahoo@gmail.com");
		map1.put("pass","S.saty@6991");
		map1.put("product","Adidas Original");
//		Object [][] o = new Object [][]
//		return o*/
		List<HashMap<String,String>> value = getJsonData(System.getProperty("user.dir")
				+"//src//test//java//EcommerceFramework//TestData//TestDataJson.json");
		return new Object[][] {{value.get(0)},{value.get(1)}};
	}

}
