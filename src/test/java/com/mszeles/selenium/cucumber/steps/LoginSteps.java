/*
 * LoginSteps.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.cucumber.steps;

import com.mszeles.selenium.framework.BaseTest;
import com.mszeles.selenium.repository.HomePageModel;
import com.mszeles.selenium.repository.LoginPageModel;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseTest {

	private HomePageModel homePage;

	@Given("Click on Login link in homepage to land on secure sign in page")
	public void click_on_login_link_in_homepage_to_land_on_secure_sign_in_page() {
		homePage = new HomePageModel(getDriver());
		homePage.openLoginPage();
	}
	@When("User enters {string} and {string} and logs in")
	public void user_enters_and_and_logs_in(String username, String password) {
		LoginPageModel loginPage = new LoginPageModel(getDriver());
		loginPage.login(username, password);
	}
	@Then("Verify that user is successfully logged in")
	public void verify_that_user_is_successfully_logged_in() {
	}
}
