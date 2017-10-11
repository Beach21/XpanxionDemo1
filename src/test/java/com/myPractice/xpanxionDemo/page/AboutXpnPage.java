package com.myPractice.xpanxionDemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * About Page Object
 * 
 * <P>
 * User comes here to after clicking the "About" link on the Login Page
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class AboutXpnPage {

	/** Variables and constants */
	final WebDriver driver;

	/** Locators */
	@FindBy(how = How.XPATH, using = "username")
	public WebElement txtbx_UserName;

	/** Methods */
	public AboutXpnPage(WebDriver driver) {

		this.driver = driver;
	}

	public String getPageURL() {

		return driver.getCurrentUrl();
	}

	public boolean isAboutPageLoaded(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		String aboutURLString = "pages/about";

		if (currentURL.contains(aboutURLString)) {
			return true;
		}

		else {
			System.out.println("Not reached 'About' page");
			System.out.println("About URL substring is:  " + aboutURLString);
			System.out.println("Current page is: " + currentURL);
			return false;
		}
	}

}
