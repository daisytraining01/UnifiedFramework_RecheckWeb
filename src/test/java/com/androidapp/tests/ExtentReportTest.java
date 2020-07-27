package com.androidapp.tests;

import java.awt.Color;
import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.maveric.core.cucumber.reporter.ExtentReportGenerator;

public class ExtentReportTest{

    private String user_name = "standard_user";
    private String password = "secret_sauce";
    ExtentHtmlReporter htmlReporter;
    ExtentReports html;
    ExtentTest Feature;
    ExtentTest scenario;
    
    @BeforeTest
    public void setup() throws ClassNotFoundException {
    	//htmlReporter=new ExtentHtmlReporter("D:\\Test\\SampleExtentReport.html");
    	//html=new ExtentReports();
    	//html.attachReporter(htmlReporter);
    	
    	//Feature=html.createTest(new GherkinKeyword("Feature"),ExtentReportGenerator.featureDesc);
    }
    
    
    @Test
    public void loginWithValidCredentials() throws IOException, ClassNotFoundException {
    
    	
    	ExtentReportGenerator.generateCucumberReport();
    	
    }
    
    @Test
    public void loginWithValidCredentials2() throws IOException, ClassNotFoundException {
    	
    	/*scenario=Feature.createNode(new GherkinKeyword("Scenario"),"TC_001-Perform Payment Transaction");
    	scenario.createNode(new GherkinKeyword("Given"), "Jeff has bought a microwave for $100").pass("pass");
    	scenario.createNode(new GherkinKeyword("And"), "he has a receipt").pass("pass");
    	scenario.createNode(new GherkinKeyword("When"), "he returns the microwave").pass("pass");
    	scenario.createNode(new GherkinKeyword("Then"), "Jeff should be refunded $100").fail("fail");
    	html.flush();*/
    }

}
