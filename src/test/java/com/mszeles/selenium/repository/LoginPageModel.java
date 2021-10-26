/*
 * HomePageModel.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageModel {

	private final WebDriver driver;

	@FindBy(id = "user_email")
	private WebElement email;

	@FindBy(id = "user_password")
	private WebElement password;

	@FindBy(css = "input[type='submit']")
	private WebElement loginButton;

	@FindBy(css = ".link-below-button")
	private WebElement forgotPassword;

	public LoginPageModel(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getEmail() {
		return email;
	}


	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public void login(String username, String password) {
		getEmail().sendKeys(username);
		getPassword().sendKeys(password);
		getLoginButton().click();
	}

	public ResetPasswordPageModel clickForgotPassword() {
		forgotPassword.click();
		return new ResetPasswordPageModel(driver);
	}

}
