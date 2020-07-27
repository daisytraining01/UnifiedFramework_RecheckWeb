package com.maveric.core.testng;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import com.maveric.core.testng.listeners.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class TestNGBaseTest extends BaseTest{
	
	@AfterMethod
    public synchronized void afterMethod(ITestResult result) {
        
        switch (result.getStatus()) {
        case ITestResult.FAILURE:
        	Reporter.log(result.getMethod().getMethodName()+"Failed");
            break;
        case ITestResult.SKIP:
        	Reporter.log(result.getMethod().getMethodName()+"Skipped");
            break;
        case ITestResult.SUCCESS:
        	Reporter.log(result.getMethod().getMethodName()+"Passed");
            break;
        default:
        	Reporter.log("Unexpected result status: {}", result.getStatus());
    }
    }
	
	

}
