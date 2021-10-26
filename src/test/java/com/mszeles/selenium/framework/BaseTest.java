/*
 * BaseTest.java
 *
 * Copyright 2001-2008 NETAVIS Kft. All rights reserved.
 * NETAVIS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.mszeles.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {
	public WebDriver driver;
	protected Properties properties;

	public void initalizeDriver() throws FileNotFoundException, IOException {
		properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/framework.properties"));
		//String browser = (String)properties.get("browser");
		String browser = System.getProperty("browser","chrome");
		String headless = System.getProperty("headless","false");
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/libs/geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if ("true".equals(headless)) {
				firefoxOptions.setHeadless(true);
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "IE":
			throw new UnsupportedOperationException("IE is not supported");
		default:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/libs/ChromeDriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			if ("true".equals(headless)) {
				chromeOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chromeOptions);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		driver.manage().window().maximize();
	}

	public static void takeScreenshot(WebDriver driver, String path) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(path));
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
