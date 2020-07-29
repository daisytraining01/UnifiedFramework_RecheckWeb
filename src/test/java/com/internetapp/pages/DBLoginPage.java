package com.internetapp.pages;

import com.maveric.core.config.ConfigProperties;
import com.maveric.core.driver.Driver;
import com.maveric.core.driver.DriverFactory;
import com.maveric.core.utils.web.WebActions;

import de.retest.recheck.RecheckImpl;
import de.retest.web.selenium.RecheckDriver;
import de.retest.web.selenium.UnbreakableDriver;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    public RecheckImpl recheckin;
    
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
        ;
        log("sample log");

        return this;

    }
    
    public DBLoginPage navigateUpdated(String url,String driverType) throws InterruptedException {
    	
    	//DriverFactory.downloadDriver();
    	//System.setProperty("webdriver.chrome.driver", "D:\\Test\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.driver", "D:\\workspace\\UnifiedFramework3\\DemoPrepLatest\\Drivers\\chromedriver.exe");
    	recheckin = new RecheckImpl();
    	
    	if(driverType.contains("desktop")) {
    		this.driver = driverFactory.driverSetup();
    		//driver = new ChromeDriver();
    		//driver = new RecheckDriver(new ChromeDriver()); 
    		driver = new RecheckDriver((RemoteWebDriver) this.driver); 	
    		//driver = new UnbreakableDriver( (RemoteWebDriver) this.driver );
    	}
    	
    	if(driverType.contains("mobile")) {
    		
    		System.out.println("Mobile Driver to be set");
/*    		if (driver.toString()!= ""){ 
    			driver.quit();
    			System.out.println("Quit the existing driver");
    			Thread.sleep(2000);
    		}*/
    		this.driver=driverFactory.mobdriverSetup();
    		driver = new RecheckDriver((RemoteWebDriver) this.driver); 
    		System.out.println("mobile driver is ready");
    	}
    	
		System.out.println("desktop driver is ready");
		recheckin.startTest();
		System.out.println("### recheckin start ###");
		
    	System.out.println("Driver Instantiated is : " + driver);
    	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.navigate().to(url);
        recheckin.check( driver, "initial" );
        recheckin.capTest();
        wait=new WebDriverWait(driver, 30);
        
        System.out.println("$$$$$ Navigated to the URL $$$$$");
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
