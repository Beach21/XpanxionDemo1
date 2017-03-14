package com.myPractice.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver = null;
	public WebDriver getDriver(){		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Work\\Practice\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}	
	
}
