package com.myPractice.demo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.myPractice.demo.Base;

public class LoginPage {

	//First change
	
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

	/** Constructor */
	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */

	public void login(String sUserName, String sPassword) {

		txtbx_UserName.sendKeys(sUserName);
		txtbx_Password.sendKeys(sPassword);
		btn_Login.click();
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
	
	

}
