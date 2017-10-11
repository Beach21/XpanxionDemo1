package com.myPractice.xpanxionDemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Login Page Object
 * 
 * <P>
 * User comes here before any other page
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class CareersXpnPage {

	/** Variables and constants */
	final WebDriver driver;

	/** Locators */
	@FindBy(how = How.ID, using = "username")
	public WebElement txtbx_UserName;

	@FindBy(how = How.ID, using = "password")
	public WebElement txtbx_Password;

	@FindBy(how = How.NAME, using = "btn-login")
	public WebElement btn_Login;

	@FindBy(how = How.XPATH, using = "//a[contains(@href, 'create')]")
	public WebElement lnk_CreateAcct;

	@FindBy(how = How.XPATH, using = "//a[contains(@href, 'forgot')]")
	public WebElement lnk_ResetPassword;

	@FindBy(how = How.XPATH, using = "//a[contains(@href, 'about')]")
	public WebElement lnk_About;

	@FindBy(how = How.XPATH, using = "//div[contains(@class, 'err')]")
	public WebElement div_ErrMsg_MssngNamePassword;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Succesfully')]")
	public WebElement div_SuccessfullyCreatedAccount;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Password Updated')]")
	public WebElement div_PasswordUpdated;

	/** Constructor */
	public CareersXpnPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */

	public ServicesXpnPage login(String sUserName, String sPassword) {

		txtbx_UserName.sendKeys(sUserName);
		txtbx_Password.sendKeys(sPassword);
		btn_Login.click();

		return PageFactory.initElements(driver, ServicesXpnPage.class);
	}

	/*
	 * Checks if div "Successfully Created Account" is visible on the page and
	 * indirectly that new account is created successfully
	 */
	public boolean isAccountCreated() {

		boolean isDivSuccessfullyCreatedAccountPresent = false;
		WebElement bn = div_SuccessfullyCreatedAccount;

		System.out.println(bn.getText());

		if (bn != null) {
			isDivSuccessfullyCreatedAccountPresent = true;
		}

		return isDivSuccessfullyCreatedAccountPresent;
	}
	
	/*
	 * Checks if div "Password Updated" is visible on the page and
	 * indirectly that password is updated successfully
	 */
	public boolean isPasswordUpdated() {

		boolean isDivPasswordUpdatedPresent = false;
		WebElement bn = div_PasswordUpdated;

		System.out.println(bn.getText());

		if (bn != null) {
			isDivPasswordUpdatedPresent = true;
		}

		return isDivPasswordUpdatedPresent;
	}

	public WebElement getBadNamePassWordMsg() {

		if (div_ErrMsg_MssngNamePassword != null) {
			System.out.println("Error message is: " + div_ErrMsg_MssngNamePassword.getText());
			return div_ErrMsg_MssngNamePassword;
		} else {
			System.out.println("Error message for invalid name/password not displayed");
			return null;
		}
	}

	public boolean isPasswordRequired() {

		if (txtbx_Password.getAttribute("required") != null) {
			System.out.println("Password field is required");
			return true;
		} else {
			System.out.println("Password field is not required");
			return false;
		}
	}

	// Clicks on "Create Account" link and returns a Create Account Page object
	public boolean clickCreateAcct() {

		try {
			WebElement ca = lnk_CreateAcct;
			ca.click();
			driver.wait(6000);

		} catch (Exception e) {
			// If element not found
			System.out.println(e.getMessage());
			System.out.println("Create Account link not found");
		}

		return true;

	}

	// Clicks on "Create Account" link and returns a Create Account Page object
	public HomeXpnPage createAccount() {

		try {
			WebElement ca = lnk_CreateAcct;
			ca.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(" 'Create Account' link could not be clicked successfully");
		}

		return PageFactory.initElements(driver, HomeXpnPage.class);
	}

	// Returns an About page object
	public AboutXpnPage clickLnkAbout() {

		try {
			WebElement ca = lnk_About;
			ca.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(" 'About' link could not be clicked successfully");
		}

		return PageFactory.initElements(driver, AboutXpnPage.class);
	}

	// Returns a Forgot Password page object
	public ApproachXpnPage clickLnkResetPassword() {

		try {
			WebElement rp = lnk_ResetPassword;
			rp.click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(" 'Reset Password' link could not be clicked successfully");
		}

		return PageFactory.initElements(driver, ApproachXpnPage.class);
	}

	/*
	 * Checks if the user name text box is visible on the page and indirectly
	 * that the Login page is loaded
	 */
	public boolean isLoginPageLoaded() {

		WebElement un = txtbx_UserName;

		if (un != null) {
			return true;
		}

		else {
			return false;
		}
	}

}