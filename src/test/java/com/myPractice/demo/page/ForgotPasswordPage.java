package com.myPractice.demo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Forgot Password Page Object
 * 
 * <P>
 * User comes here from Login Page
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class ForgotPasswordPage {

	/** Variables and constants */
	final WebDriver driver;

	/** Locators */
	@FindBy(how = How.ID, using = "SecurityQuestionID")
	public WebElement drpDwn_SecurityQuestion;

	/** Constructor */
	public ForgotPasswordPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */
	public String getPageURL() {

		return driver.getCurrentUrl();
	}

	// Checks if the navigation to the page is correct

	public boolean isForgotPWPageURLNavigated(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		String forgotPasswordURLString = "forgot_password";

		if (currentURL.contains(forgotPasswordURLString)) {
			return true;
		}

		else {
			System.out.println("Not reached 'About' page");
			System.out.println("'Forgot Password' URL substring is:  " + forgotPasswordURLString);
			System.out.println("Current page is: " + currentURL);
			return false;
		}
	}

	/*
	 * Checks if security question drop down is visible on the page and
	 * indirectly that the Create Account page is loaded
	 */
	public boolean isForgotPasswordPageLoaded() {

		boolean isSecQuestnDDPresent = false;
		WebElement sq = drpDwn_SecurityQuestion;

		System.out.println(sq.getText());

		if (sq != null) {
			isSecQuestnDDPresent = true;
		}

		return isSecQuestnDDPresent;
	}

}
