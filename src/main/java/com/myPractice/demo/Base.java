package com.myPractice.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Base Class
 * 
 * <P>
 * This class is instantiated in the test class
 * <P>
 * Configuration, loading of properties file and WebDriver definition is done here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class Base {

	public Properties prop = new Properties();
	public InputStream input = null;
	public WebDriver driver = null;
	public String configPropertyPath = "/com/myPractice/config.properties";

	public WebDriver getDriver() {

		/* Load the config file */
		loadProperties(configPropertyPath);

		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
		driver = new ChromeDriver();

		return driver;
	}

	public WebDriver getDriver(String driverType) {

		/* Load the config file */
		loadProperties(configPropertyPath);

		if (driverType.contains("Chrome")) {

			driverType = "chromeDriverPath";

			try {
				System.setProperty("webdriver.chrome.driver", prop.getProperty(driverType));
				driver = new ChromeDriver();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}

		} else if (driverType.contains("Firefox")) {

			driverType = "FirefoxDriverPath";

			try {
				System.setProperty("webdriver.gecko.driver", prop.getProperty(driverType));
				driver = new MarionetteDriver();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
		} else if (driverType.contains("IEDriver")) {

			driverType = "IEDriverPath";

			try {
				System.setProperty("webdriver.ie.driver", prop.getProperty(driverType));
				driver = new InternetExplorerDriver();
			} catch (Exception e) {

				System.out.println(e.getMessage());
			}
		}

		else {
			System.out.println("Driver not found");
		}

		return driver;
	}

	void loadProperties(String Path) {

		try {

			prop.load(getClass().getResourceAsStream("/config.properties"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
