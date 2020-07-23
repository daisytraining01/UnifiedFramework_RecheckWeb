package com.maveric.core.testng;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.maveric.core.testng.listeners.DriverListener;

import com.maveric.core.testng.listeners.ReportListener;
import com.maveric.core.testng.listeners.RestListener;
import com.maveric.core.testng.listeners.TestListener;
import com.maveric.core.testng.listeners.ExtentReportListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

//@Listeners({TestListener.class, ReportListener.class,ExtentReportListener.class,DriverListener.class, RestListener.class})
@Listeners({TestListener.class,ReportListener.class,ExtentITestListenerClassAdapter.class,DriverListener.class, RestListener.class})
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
