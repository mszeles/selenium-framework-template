/*
 * HomePage.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium;

import static org.testng.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mszeles.selenium.framework.BaseTest;
import com.mszeles.selenium.repository.HomePageModel;
import com.mszeles.selenium.repository.LoginPageModel;
import com.mszeles.selenium.repository.ResetPasswordPageModel;

public class HomePage extends BaseTest {
	private static final Logger log = LoggerFactory.getLogger(HomePage.class);
	private HomePageModel homePage;

	@BeforeMethod
	public void openPage() {
		String url = properties.getProperty("url");
		driver.get(url);
		log.info("Navigated to {}.", url);
		homePage = new HomePageModel(driver);
	}

	@Test
	public void checkSubtitle() {
		assertEquals(homePage.getSubTitle().getText(), "Learn Hot tools like Selenium, Appium, JMeter, SoapUI,Database Testing and more..", "SubTitle mismatch");
	}

	@Test(dataProvider = "getData")
	public void basePageNavigation(String username, String password, String text) {
		homePage = new HomePageModel(driver);
		assertEquals(homePage.getTitle().getText(), "FEATURED COURSES", "Title mismatch");
		log.info("Succesfully navigated title");
		assertTrue(homePage.getNavigationBar().isDisplayed());
		LoginPageModel loginPage = homePage.openLoginPage();
		loginPage.login(username, password);
		log.info(text);
	}

	@Test
	public void forgotPassword() {
		homePage = new HomePageModel(driver);
		assertTrue(homePage.getNavigationBar().isDisplayed());
		LoginPageModel loginPage = homePage.openLoginPage();
		ResetPasswordPageModel forgotPasswordPage = loginPage.clickForgotPassword();
		forgotPasswordPage.resetPassword("aaa@aa");
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0] = "a@a";
		data[0][1] = "a";
		data[0][2] = "Restricted user";
		data[1][0] = "b@b";
		data[1][1] = "b";
		data[1][2] = "Non restricted user";
		return data;
	}

}
