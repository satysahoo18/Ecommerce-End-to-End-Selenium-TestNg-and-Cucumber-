package EcommerceFramework.TestComponents;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import EcommerceFramework.pageObject.Login;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class BaseTest {
	public WebDriver driver;
	public Login login;
	
    public WebDriver driverInitializer() throws IOException {
	Properties prop = new Properties();
	FileInputStream file = new FileInputStream(System.getProperty("user.dir") +
			"\\src\\main\\java\\EcommerceFramework\\Resources\\GlobalData.properties");
	prop.load(file);
	
    String browserName =System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
	if(browserName.contains("chrome"))
	{
		ChromeOptions options =new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(browserName.contains("headless")) {
			options.addArguments("headless");
			
		}
			
		
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
	}
	else if(browserName.contains("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}
	else if(browserName.contains("firefox")) {
		WebDriverManager.firefoxdriver().setup();;
		driver = new FirefoxDriver();
	}
	
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	return driver;
 }
    @BeforeMethod(alwaysRun = true)
    public Login launchApplication() throws IOException {
    	 driver = driverInitializer();
    	login = new Login(driver);
    	 login.goTo("https://rahulshettyacademy.com/client");
    	 return login;
    	 
    }
    
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
   	 driver.quit();
   }
    
   
	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException{
		String jsonData = FileUtils.readFileToString(new File(filePath), 
				StandardCharsets.UTF_8);
		
		//Using Jackson Databind Package
		ObjectMapper obj =new ObjectMapper();
		List <HashMap<String,String>> value = obj.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return value;
    }
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot)driver;
		File image  = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(image, new File(System.getProperty("user.dir")+"//Reports//"+ testCaseName + ".png"));
		return System.getProperty("user.dir")+"//Reports//"+ testCaseName + ".png";
	}
	
public static ExtentReports extentReportDeclare() {
		
		String path = System.getProperty("user.dir") + "//Reports//index.html";
		ExtentSparkReporter sp = new ExtentSparkReporter(path);
		sp.config().setDocumentTitle("Ecommerce Testing");
		sp.config().setReportName("WebApplication Testing");
		
		ExtentReports er = new ExtentReports();
		er.attachReporter(sp);
		return er;
	}

}
