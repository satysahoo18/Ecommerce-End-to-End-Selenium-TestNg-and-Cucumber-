package EcommerceFramework.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
int i =0;
	@Override
	public boolean retry(ITestResult result) {
		if (i<2) {
			i++;
			return true;
		}
		return false;
	}	
}
