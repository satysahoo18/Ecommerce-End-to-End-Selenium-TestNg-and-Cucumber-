package EcommerceFramework.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportPrep {
	public static  ExtentReports extentReportDeclare() {
		
		String path = System.getProperty("user.dir") + "//Reports//index.html";
		ExtentSparkReporter sp = new ExtentSparkReporter(path);
		sp.config().setDocumentTitle("Ecommerce Testing");
		sp.config().setReportName("WebApplication Testing");
		
		ExtentReports er = new ExtentReports();
		er.attachReporter(sp);
		return er;
	}

}
