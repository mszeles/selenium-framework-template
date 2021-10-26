/*
 * LoginSteps.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.cucumber.steps;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mszeles.selenium.framework.BaseTest;
import com.mszeles.selenium.repository.AirconvertpdftojpgHomepageModel;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UploadSteps extends BaseTest {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private AirconvertpdftojpgHomepageModel homePage;

	@When("Click on Choose file")
	public void click_on_choose_file() {
		homePage = new AirconvertpdftojpgHomepageModel(getDriver());
		homePage.clickOnUploadButton();
	}
	@When("Select a file")
	public void select_a_file() throws IOException {
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String command = System.getProperty("user.dir") + "/src/test/resources/autoit/FileUpload.exe \""
				+ System.getProperty("user.dir") + "\\src\\test\\resources\\Visio-TestLinkArchitecture.pdf\"";
		log.info("Uploading file with command: " + command);
		Runtime.getRuntime().exec(command);
	}
	@When("Click on Convert Now button")
	public void click_on_convert_now_button() {
		homePage.clickOnConvertNow();
	}
	@Then("Verify that file is uploaded")
	public void verify_that_file_is_uploaded() {
		homePage.waitForDownloadLinkToAppear();
	}

	@Then("Click on download button")
	public void click_on_download_button() {
		homePage.clickOnDownload();
	}
	@Then("Verify file is downloaded")
	public void verify_file_is_downloaded() {

	}

	//	@Override
	//	public void navigate_to_site(String url) {
	//		super.navigate_to_site(url);
	//		log.info("Navigated to {}.", url);
	//		homePage = new AirconvertpdftojpgHomepageModel(driver);
	//	}
	//
	//	@Given("Click on Login link in homepage to land on secure sign in page")
	//	public void click_on_login_link_in_homepage_to_land_on_secure_sign_in_page() {
	//		homePage.openLoginPage();
	//	}
	//	@When("User enters {string} and {string} and logs in")
	//	public void user_enters_and_and_logs_in(String username, String password) {
	//		LoginPageModel loginPage = new LoginPageModel(driver);
	//		loginPage.login(username, password);
	//	}
	//	@Then("Verify that user is successfully logged in")
	//	public void verify_that_user_is_successfully_logged_in() {
	//	}
	//
	//	@Then("Close browser")
	//	public void close_browser() {
	//		driver.close();
	//	}
}
