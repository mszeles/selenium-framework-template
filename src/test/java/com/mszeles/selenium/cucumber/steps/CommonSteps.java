/*
 * LoginSteps.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.cucumber.steps;

import com.mszeles.selenium.framework.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps extends BaseTest {

	@Given("Navigate to {string} site")
	public void navigate_to_site(String url) {
		getDriver().get(url);
	}

	@Then("Close browser")
	public void close_browser() {
		closeBrowser();
	}
}
