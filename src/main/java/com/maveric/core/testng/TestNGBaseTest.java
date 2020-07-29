package com.maveric.core.testng;

import org.testng.annotations.Listeners;

import com.maveric.core.testng.listeners.ExtentReportListener;

@Listeners(ExtentReportListener.class)
public class TestNGBaseTest{

}