package EcommerceFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import EcommerceFramework.Resources.ExtentReportPrep;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	WebDriver driver;
	ThreadLocal<ExtentTest> ext = new ThreadLocal<ExtentTest>();
	
	ExtentReports er = ExtentReportPrep.extentReportDeclare();

	@Override
	public void onTestStart(ITestResult result) {
		
		test = er.createTest(result.getMethod().getMethodName());
		
		ext.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ext.get().log(Status.PASS,result.getTestName() + "is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ext.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = null;
		try {
			path = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ext.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
//	  test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		er.flush();
	}

}
