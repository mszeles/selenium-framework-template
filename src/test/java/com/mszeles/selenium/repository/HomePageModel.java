/*
 * HomePageModel.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.repository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageModel {

	private final WebDriver driver;

	@FindBy(xpath = "//span[text()='Login']")
	private WebElement login;

	@FindBy(xpath = "//button[text()='NO THANKS']")
	private List<WebElement> newsletterNoThanks;

	@FindBy(css = ".text-center>h2")
	private WebElement title;

	@FindBy(css = "div.video-banner p")
	private WebElement subTitle;

	@FindBy(css = ".nav.navbar-nav.navbar-right")
	private WebElement navigationBar;

	public HomePageModel(WebDriver driver) {
		this.driver = driver;
		//		AjaxElementLocatorFactory  factory = new AjaxElementLocatorFactory(driver, 10);
		PageFactory.initElements(driver, this);
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getSubTitle() {
		return subTitle;
	}

	public WebElement getNavigationBar() {
		return navigationBar;
	}

	public LoginPageModel openLoginPage() {
		if (!newsletterNoThanks.isEmpty()) {
			newsletterNoThanks.get(0).click();
		}
		login.click();
		return new LoginPageModel(driver);
	}


}
