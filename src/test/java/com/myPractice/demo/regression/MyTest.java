package com.myPractice.demo.regression;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.myPractice.demo.Base;
import com.myPractice.demo.page.LoginPage;

import com.myPractice.demo.page.AboutPage;
import com.myPractice.demo.page.CreateAccountPage;
import com.myPractice.demo.page.ForgotPasswordPage;

public class MyTest {

	public WebDriver d = null;
	String baseURL = "";	
	Base b = new Base();	
	
	@BeforeSuite
	void suiteSetUp(){
		
		baseURL = "https://www.leagueplanit.com";
	}
	
	@AfterSuite
	void suiteCleanUp(){
		if (d != null) {
			d.quit();
		}
	}

	@BeforeMethod
	 void setUp() {		
		
		d = b.getDriver();
		//d.manage().window().maximize();
		d.get(baseURL);
		d.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

	}
	

	@AfterMethod
	protected void cleanUp() {

		if (d != null) {
			d.quit();
		}
	}		


	@Test
	public void test01() {
		
		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badUserNameString = "User";
		boolean isUserNameinMsg = true;

		loginPage.login("invalidUserName", "password");
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isUserNameinMsg = StringUtils.containsIgnoreCase(errorMessageString, badUserNameString);
		Assert.assertTrue(isUserNameinMsg, "Correct message not displayed for invalid user name");

	}

	@Test

	public void testInvalidPassword() {
		
		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badPasswordString = "password";
		boolean isPasswordinMsg = false;

		loginPage.login("Username", "invalidPassword");
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isPasswordinMsg = StringUtils.containsIgnoreCase(errorMessageString, badPasswordString);
		Assert.assertTrue(isPasswordinMsg, "Correct message is not displayed for incorrect password");
	}

	@Test

	public void testInvalidUsrNmPassWd() {
		
		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badUserNameString = "username";
		String badPasswordString = "password";
		boolean isUserNameinMsg = false;
		boolean isPasswordinMsg = false;
		boolean isUserNameAndPassWdinMsg = false;

		loginPage.login("invalidUserName", "invalidPassword");
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isUserNameinMsg = StringUtils.containsIgnoreCase(errorMessageString, badUserNameString);
		isPasswordinMsg = StringUtils.containsIgnoreCase(errorMessageString, badPasswordString);

		// Make sure both user name and password are mentioned in the error
		// message
		isUserNameAndPassWdinMsg = (isUserNameinMsg) && (isPasswordinMsg);
		Assert.assertTrue(isUserNameAndPassWdinMsg,
				"Correct message is not displayed for incorrect user name and password");
	}

	@Test

	public void testBlankUserName() {
		
		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badUserNameString = "User";
		boolean isUserNameinMsg = false;

		// Blank user name
		loginPage.login("", "password");
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isUserNameinMsg = StringUtils.containsIgnoreCase(errorMessageString, badUserNameString);
		Assert.assertTrue(isUserNameinMsg, "Correct message is not displayed for incorrect user name");
	}

	@Test

	public void testBlankPassword() {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		
		boolean passwordRequirement = loginPage.isPasswordRequired();
		Assert.assertTrue(passwordRequirement, "Password field is not a required field");
	}
	
	 /* Tests to access other pages  */
	  
	  @Test
	  
	 public void testAccessCreateAccountPg() { 
		
		  LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		  boolean isCreateAccountPageLoaded = false;
		  CreateAccountPage ca = loginPage.createAccount();	
		  isCreateAccountPageLoaded = ca.isCreateAccountPageLoaded();
		  
		  Assert.assertTrue(isCreateAccountPageLoaded, "Create Account page not loaded");	 
	  }
	  
	  @Test
	  
	  public void testAccessAboutPg() { 
	 			  
		  LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		  boolean isAboutPageLoaded = false;
	 	  AboutPage ap = loginPage.clickLnkAbout();	
	 	  isAboutPageLoaded = ap.isAboutPageLoaded(d);
	 	  
	 	  Assert.assertTrue(isAboutPageLoaded, " 'About' page not loaded");	 
	   }
	  
	@Test
	  
	  public void testAccessForgotPasswordPg() { 
	 	
		  LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
	 	  boolean isForgotPasswordPageLoaded = false;
	 	  ForgotPasswordPage fp = loginPage.clickLnkResetPassword();	
	 	  isForgotPasswordPageLoaded = fp.isForgotPasswordPageLoaded();
	 	  
	 	  Assert.assertTrue(isForgotPasswordPageLoaded, "Create Account page not loaded");	 
	   }  

	}


