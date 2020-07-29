package com.androidapp.tests;

import com.androidapp.pages.LoginPage;
import com.androidapp.pages.ProductPage;
import com.maveric.core.driver.Driver;
import com.maveric.core.driver.DriverFactory;
import com.maveric.core.driver.RemoteCapabilities;
import com.maveric.core.testng.BaseTest;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidTests extends BaseTest {

    private String user_name = "standard_user";
    private String password = "secret_sauce";
    public DriverFactory driverFactory = new DriverFactory();
    RemoteCapabilities remoteCapabilities = new RemoteCapabilities(DriverFactory.Platform.android , DriverFactory.Browser.chrome);
    
    @BeforeMethod(alwaysRun = true)
    private void testbeforeMethod() {
    	System.out.println("My Test Before Method");
    	driverFactory.createRemoteDriverUpdated();   	
    	
    }

    @Test(enabled = true, groups = {"android"})
    public void loginWithValidCredentials() {
    	System.out.println("ENTERED TEST********************");
    	//remoteCapabilities.driver.rotate(ScreenOrientation.PORTRAIT);
    	remoteCapabilities.driver.navigate().back();
    	//remoteCapabilities.driver.findElement(By.xpath("//*[.='OK']")).click();
    	System.out.println("&&& Clicked on OK Button &&&");
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
    	remoteCapabilities.driver.hideKeyboard();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='loginButton']")).click();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("0541234567");
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Jon Snow");
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("50");
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='countryButton']")).click();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@text='Switzerland']")).click();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
    	remoteCapabilities.driver.findElement(By.xpath("//*[@text='Yes']")).click();
    }

/*    @Test(groups = {"android"})
    public void loginWithInvalidCredentials() {
        String user_name = "samUser";
        String password = "PasswordSam";
        AppiumDriver<?> driver = Driver.getAppiumDriver();
        LoginPage page = new LoginPage(driver);
        page.isOnLoginPage();
        page.login(user_name, password);
        page.verifyInvalidLogin();
    }*/

/*    @Test(groups = {"android"})
    public void checkoutProduct() {
        String firstName = "sai";
        String lastName = "kiran";
        String zipCode = "12345";
        AppiumDriver<?> driver = Driver.getAppiumDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.isOnLoginPage();
        loginPage.login(user_name, password);
        loginPage.isOnProductsPage();
        ProductPage prdPage = new ProductPage(driver);
        prdPage.checkoutProduct(firstName, lastName, zipCode);
        loginPage.isOnProductsPage();
        loginPage.openMenu();
        loginPage.logout();
        loginPage.isOnLoginPage();
    }*/
}
