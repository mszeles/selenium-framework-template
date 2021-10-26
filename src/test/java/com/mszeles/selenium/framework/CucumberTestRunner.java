/*
 * TestRunner.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.framework;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/java/com/mszeles/selenium/cucumber/features/UploadFile.feature", glue = "com.mszeles.selenium.cucumber.steps")
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
	//	@Override
	//	@DataProvider()
	//	public Object[][] scenarios() {
	//		return super.scenarios();
	//	}
}
