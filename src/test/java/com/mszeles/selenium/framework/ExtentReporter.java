/*
 * ExtentReporter.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	private static ExtentReports reports;

	public static ExtentReports getReportObject() {
		//ExtentReports
		//ExtentSparkReporter
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/index.html");
		reporter.config().setReportName("Web automation results");
		reporter.config().setDocumentTitle("Test Results");
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Miki");
		return reports;

	}

}
