package com.internetapp.tests;

import com.api.service.Reqres;
import com.codoid.products.exception.FilloException;
import com.internetapp.pages.DBLoginPage;
import com.maveric.core.testng.BaseTest;
import com.maveric.core.testng.TestNGBaseTest;
import com.maveric.core.utils.data.ExcelDataReader;

import org.eclipse.persistence.internal.libraries.asm.commons.Method;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoBankTest extends TestNGBaseTest{
	
	Reqres restapi=new Reqres();
    public static String url="";
	
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"TC001"}
		};
	}


    @Test(groups = {"web"},dataProvider="inputs")
    public void verifyLoginWithInvalidCredentials(String tcId) throws FilloException {
    	
    	System.out.println("Test Method1");

       new DBLoginPage().navigateUpdated("http://dbankdemo.com/login", "desktop-web").login(ExcelDataReader.getData("Sheet1", tcId,"SrcUserName"),
    		   ExcelDataReader.getData("Sheet1", tcId,"SrcPassword"))
       .checkSavingsBalance(ExcelDataReader.getData("Sheet1", tcId,"SourceAcc"),restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", tcId,"EmployeeId")))
       .transferAmount(ExcelDataReader.getData("Sheet1", tcId,"SourceAcc"),
				 ExcelDataReader.getData("Sheet1", tcId,"DestinationAcc"),
				 restapi.getEmployeeSalary(ExcelDataReader.getData("Sheet1", tcId,"EmployeeId")));
       
      
       
       
    }
    
    @Test(groups = {"web","chrome"})
    public void verifySampleTest1() throws FilloException {
    	
    	System.out.println("Test Method2");

    	new DBLoginPage().navigateUpdated("http://dbankdemo.com/login", "desktop-web");
       
      
       
       
    }
    
    
    @Test(groups = {"web","firefox"})
    public void verifySampleTest() throws FilloException {

    	System.out.println("Test Method2");
    	new DBLoginPage().navigateUpdated("http://dbankdemo.com/login", "desktop-web");
       
       
    }


	
}

