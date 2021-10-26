/*
 * LoginSteps.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.cucumber.steps;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mszeles.selenium.framework.BaseTest;
import com.mszeles.selenium.repository.HomePageModel;
import com.mszeles.selenium.repository.LoginPageModel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest {
	private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);

	private HomePageModel homePage;

	@Given("Initalize the browser with chrome")
	public void initalize_the_browser_with_chrome() throws FileNotFoundException, IOException {
		initalizeDriver();
	}
	@Given("Navigate to {string} site")
	public void navigate_to_site(String url) {
		driver.get(url);
		log.info("Navigated to {}.", url);
		homePage = new HomePageModel(driver);
	}

	@Given("Click on Login link in homepage to land on secure sign in page")
	public void click_on_login_link_in_homepage_to_land_on_secure_sign_in_page() {
		homePage.openLoginPage();
	}
	@When("User enters {string} and {string} and logs in")
	public void user_enters_and_and_logs_in(String username, String password) {
		LoginPageModel loginPage = new LoginPageModel(driver);
		loginPage.login(username, password);
	}
	@Then("Verify that user is successfully logged in")
	public void verify_that_user_is_successfully_logged_in() {
	}

	@Then("Close browser")
	public void close_browser() {
		driver.close();
	}
}
