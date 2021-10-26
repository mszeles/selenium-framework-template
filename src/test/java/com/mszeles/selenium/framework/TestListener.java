/*
 * TestListener.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.framework;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {


	private final ExtentReports report = ExtentReporter.getReportObject();
	private final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest.set(report.createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			String path = System.getProperty("user.dir") + "/reports/" + result.getMethod().getMethodName() + ".png";
			BaseTest.takeScreenshot((WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()),
					path);
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		extentTest.get().fail(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
}
