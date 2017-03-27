package com.myPractice.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.myPractice.demo.utilClasses.ReadNewXmlData;

/**
 * Base Class
 * 
 * <P>
 * This class is the parent class of all test classes
 * <P>
 * Configuration, loading of properties file and WebDriver definition is done
 * here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class BaseTest {

	protected Properties prop = new Properties();
	protected InputStream input = null;
	protected WebDriver driver = null;
	protected String configPropertyPath = "/com/myPractice/config.properties";
	
	protected String testDataPath = "c:\\Work\\testData1.xml";
	protected String baseURL = "https://www.leagueplanit.com/test";
	protected String extentScreenshotLocation = "/test-output/ExtentScreenshot.html";
	
	

	protected WebDriver getDriver() {

		/* Load the config file */
		loadProperties(configPropertyPath);

		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
		driver = new ChromeDriver();

		return driver;
	}

	protected WebDriver getDriver(String driverType) {

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

	/* Used to get data from testData.xml file */
	protected String getTestData(String data) {

		ReadNewXmlData rd = new ReadNewXmlData();
		Document doc = rd.buildDoc(testDataPath);
		NodeList nl = doc.getElementsByTagName(data);
		Element fe = (Element) nl.item(0);
		String fs = fe.getTextContent();

		return fs;
	}

	/* Overloaded versions of get test data methods */

	protected String getTestData(String data, String path) {

		ReadNewXmlData rd = new ReadNewXmlData();
		Document doc = rd.buildDoc(path);
		NodeList nl = doc.getElementsByTagName(data);
		Element fe = (Element) nl.item(0);
		String fs = fe.getTextContent();

		return fs;
	}
	
	protected String getTestData(String data, int elementNumber) {

		ReadNewXmlData rd = new ReadNewXmlData();
		Document doc = rd.buildDoc(testDataPath);
		NodeList nl = doc.getElementsByTagName(data);
		Element fe = (Element) nl.item(elementNumber);
		String fs = fe.getTextContent();

		return fs;
	}

	protected String getTestData(String data, int elementNumber, String path) {

		ReadNewXmlData rd = new ReadNewXmlData();
		Document doc = rd.buildDoc(path);
		NodeList nl = doc.getElementsByTagName(data);
		Element fe = (Element) nl.item(elementNumber);
		String fs = fe.getTextContent();

		return fs;
	}

}
