package com.internetapp.tests;

import com.api.service.Reqres;
import com.codoid.products.exception.FilloException;
import com.internetapp.pages.DBLoginPage;
import com.maveric.core.testng.BaseTest;
import com.maveric.core.testng.TestNGBaseTest;
import com.maveric.core.utils.data.ExcelDataReader;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DemoBankTest extends TestNGBaseTest {
	
	Reqres restapi=new Reqres();
	
	@DataProvider(name="inputs",parallel=true)
	public Object[][] getData() {
		return new Object[][] {
			{"TC001"}
		};
	}

    @Test(groups = {"web"},dataProvider="inputs")
    public void verifyLoginWithInvalidCredentials(String tcId) throws FilloException, InterruptedException {

    
    System.out.println("Test Method-1"+tcId);
    
    }

    @Test(groups = {"web"},dataProvider="inputs")
    public void verifyLoginWithInvalidCredentials1(String tcId) throws FilloException, InterruptedException {

    
    System.out.println("Test Method-1"+tcId);
    
    }

}

