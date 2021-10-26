/*
 * LoginSteps.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.cucumber.steps;

import static org.testng.Assert.*;

import java.io.File;
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
		String expectedFile = System.getProperty("user.dir") + "\\1.jpg";
		log.info("Verifying existence of: " + expectedFile);
		File downloadedFile = new File(expectedFile);
		assertTrue(downloadedFile.exists());
		if (downloadedFile.exists()) {
			downloadedFile.delete();
		}
	}

}
