/*
 * AirconvertpdftojpgHomepageModel.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.repository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AirconvertpdftojpgHomepageModel {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final WebDriver driver;

	@FindBy(id = "dropzoneInput-label")
	private WebElement uploadButton;

	@FindBy(xpath = "//button[text()='Convert Now!']")
	private WebElement convertButton;

	@FindBy(css = "a[download]")
	private WebElement downloadLink;

	public AirconvertpdftojpgHomepageModel(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnUploadButton() {
		uploadButton.click();
	}

	public void clickOnConvertNow() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(convertButton));
		log.info("Convert button text: " + convertButton.getText());
		convertButton.click();
	}

	public void waitForDownloadLinkToAppear() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60), Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(downloadLink));
	}

	public void clickOnDownload() {
		downloadLink.click();
	}


}
