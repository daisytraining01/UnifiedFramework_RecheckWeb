package com.maveric.core.testng;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.maveric.core.testng.listeners.DriverListener;

@Listeners({DriverListener.class})
public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    private void beforeSuite() {
    }

    @BeforeClass(alwaysRun = true)
    private void beforeClass() {
    }

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod() {
    	
    }

}
