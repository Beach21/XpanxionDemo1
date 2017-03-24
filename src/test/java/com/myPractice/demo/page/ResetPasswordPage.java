package com.myPractice.demo.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Reset Password Page Object
 * 
 * <P>
 * User comes here from Forgot Password Page
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class ResetPasswordPage {

	/** Variables and constants */
	final WebDriver driver;

	/** Locators */

	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	public WebElement txtBx_NewPassword;

	@FindBy(how = How.XPATH, using = "//input[@id='password_confirm']")
	public WebElement txtBx_ConfirmPassword;

	@FindBy(how = How.ID, using = "btn_Submit")
	public WebElement btn_ChangePassword;

	@FindBy(how = How.NAME, using = "btn-login")
	public WebElement btn_ChangePassword1;
	
	/** Constructor */
	public ResetPasswordPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */
	public String getPageURL() {

		return driver.getCurrentUrl();
	}

	public WebElement getTxtBxPassword() {

		return txtBx_NewPassword;
	}

	public WebElement getTxtBxConfirmPassword() {

		return txtBx_ConfirmPassword;
	}

	WebElement getBtnChangePassword() {

		return btn_ChangePassword1;
	}

	// Checks if the navigation to the page is correct

	public boolean isResetPageURLNavigated(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		String resetPasswordURLString = "reset";

		if (currentURL.contains(resetPasswordURLString)) {
			return true;
		}

		else {
			System.out.println("Not reached 'About' page");
			System.out.println("'Reset Password' URL substring is:  " + resetPasswordURLString);
			System.out.println("Current page is: " + currentURL);
			return false;
		}
	}

	/*
	 * Checks if "Confirm Password" text box is present on the page and indirectly
	 * that "Reset Password" page is loaded
	 */
	public boolean isResetPasswordPageLoaded() {

		boolean isConfirmPasswordPresent = false;
		WebElement sq = txtBx_ConfirmPassword;

		System.out.println(sq.getText());

		if (sq != null) {
			isConfirmPasswordPresent = true;
		}

		return isConfirmPasswordPresent;
	}

	public LoginPage resetPasswordStep2() {

		fillNewPassword();
		fillConfirmPassword();
		clickBtnChangePassword();		

		return PageFactory.initElements(driver, LoginPage.class);

	}

	public void fillNewPassword() {

		WebElement fp = getTxtBxPassword();
		fp.click();
		
		//fp.sendKeys("pinku");
	}

	public void fillConfirmPassword() {

		WebElement sq = getTxtBxConfirmPassword();
		sq.click();
		
		sq.sendKeys("pinku");
	}	

	public void clickBtnChangePassword() {

		WebElement fp = btn_ChangePassword;
		fp.click();
	}

}
