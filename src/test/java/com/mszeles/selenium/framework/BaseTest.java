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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;

public abstract class BaseTest {
	private final static Logger log = LoggerFactory.getLogger(BaseTest.class);
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() {
		@Override
		protected WebDriver initialValue() {
			try {
				return initializeDriver();
			}
			catch (Exception e) {
				log.error("Failed to initialize webdriver", e);
				return null;
			}
		}
	};
	private static ThreadLocal<Properties> properties = new ThreadLocal<Properties>() {
		@Override
		protected Properties initialValue() {
			Properties properties = new Properties();
			try {
				properties.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/framework.properties"));
				return properties;
			}
			catch (Exception e) {
				log.error("Failed to load properties", e);
				return null;
			}
		}
	};

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static Properties getProperties() {
		return properties.get();
	}

	private static WebDriver initializeDriver() throws FileNotFoundException, IOException {
		WebDriver driver;
		String projectDir = System.getProperty("user.dir");
		getProperties().load(new FileInputStream(projectDir + "/src/test/resources/framework.properties"));
		//String browser = (String)properties.get("browser");
		String browser = System.getProperty("browser","chrome");
		String headless = System.getProperty("headless","false");
		switch (browser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectDir + "/libs/geckodriver.exe");
			FirefoxProfile profile = new FirefoxProfile();
			// Instructing firefox to use custom download location
			profile.setPreference("browser.download.folderList", 2);
			// Setting custom download directory
			profile.setPreference("browser.download.dir", projectDir);

			// Skipping Save As dialog box for types of files with their MIME
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
					"text/csv,application/java-archive, application/x-msexcel,application/excel,application/vnd.openxmlformats-officedocument.wordprocessingml.document,"
							+ "application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,"
							+ "application/vnd.microsoft.portable-executable,application/x-gzip");

			// Creating FirefoxOptions to set profile
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(profile);
			if ("true".equals(headless)) {
				firefoxOptions.setHeadless(true);
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "IE":
			throw new UnsupportedOperationException("IE is not supported");
		default:
			System.setProperty("webdriver.chrome.driver", projectDir + "/libs/ChromeDriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			if ("true".equals(headless)) {
				chromeOptions.addArguments("headless");
			}
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", projectDir);
			chromeOptions.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(chromeOptions);
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)) ;
		driver.manage().window().maximize();
		return driver;
	}

	public static void takeScreenshot(WebDriver driver, String path) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(path));
	}

	@AfterTest
	public void closeBrowser() {
		getDriver().quit();
		driver.set(null);
		properties.set(null);
	}
}
