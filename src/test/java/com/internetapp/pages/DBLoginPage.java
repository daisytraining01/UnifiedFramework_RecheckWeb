package com.internetapp.pages;

import com.maveric.core.config.ConfigProperties;
import com.maveric.core.driver.Driver;
import com.maveric.core.driver.DriverFactory;
import com.maveric.core.driver.DriverFactory.Browser;
import com.maveric.core.utils.web.WebActions;

import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.maveric.core.utils.reporter.Report.log;

import java.util.concurrent.TimeUnit;

public class DBLoginPage extends WebActions{

    private final By txt_username = By.name("username");
    private final By txt_password = By.name("password");
    private final By txt_pin = By.name("accpin");
    private final By btn_submit = By.id("submit");
    
    WebDriverWait wait;
    //WebDriver driver;
    AppiumDriver<?> mobdriver;
    DriverFactory driverFactory=new DriverFactory();
    
    
    public DBLoginPage() {
    	
    	//driver=Driver.getWebDriver();
    	//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    	
        
    }

    public DBLoginPage navigate(String url) {
        driver.navigate().to(url);

        logScreenshot("login");
        
        log("sample log");

        return this;

    }
    
    public DBLoginPage navigateUpdated(String url,String driverType) {
    	
    	//DriverFactory.downloadDriver();
    	System.setProperty("webdriver.chrome.driver", "D:\\Test\\chromedriver.exe");
    	
    	//driverFactory.createDriver(Browser.chrome);
    	
    	if(driverType.contains("desktop")) {
    		
    		driver = driverFactory.driverSetup();
    		System.out.println("desktop driver is ready");
    	}
    	if(driverType.contains("mobile")) {
    		
    		driver=driverFactory.mobdriverSetup();
    		System.out.println("mobile driver is ready");
    	}
    	System.out.println(driver);
    	
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.navigate().to(url);
        wait=new WebDriverWait(driver, 30);
        

        logScreenshot("login");
        ;
        log("sample log");
        
            
        return this;

    }
    
    
    
    public DBLoginPage navigateDiff(String url,String driverType) {
    	
    	//DriverFactory.downloadDriver();
    	System.setProperty("webdriver.chrome.driver", "D:\\Test\\chromedriver.exe");
    	
    	driver=driverFactory.createDriver(Browser.chrome);
    	
    	
    	System.out.println(driver);
    	
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.navigate().to(url);
        wait=new WebDriverWait(driver, 30);
        

        logScreenshot("login");
        ;
        log("sample log");
        
            
        return this;

    }

    public DBHomePage login(String userName,String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(txt_username))
                .sendKeys(userName);
        driver.findElement(txt_password).sendKeys(password);
        driver.findElement(btn_submit).click();
        logScreenshot("login successful");
        
        return new DBHomePage(driver);

    }
    
        
}
