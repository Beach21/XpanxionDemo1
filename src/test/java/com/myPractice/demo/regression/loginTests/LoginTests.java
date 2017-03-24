package com.myPractice.demo.regression.loginTests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.myPractice.demo.Base;
import com.myPractice.demo.page.AboutPage;
import com.myPractice.demo.page.CreateAccountPage;
import com.myPractice.demo.page.CreateAccountPageStep2;
import com.myPractice.demo.page.CreateAccountPageStep3;
import com.myPractice.demo.page.ForgotPasswordPage;
import com.myPractice.demo.page.LeaguePage;
import com.myPractice.demo.page.LoginPage;
import com.myPractice.demo.page.ResetPasswordPage;
import com.myPractice.demo.utilClasses.ReadXMLData;
import com.myPractice.demo.utilClasses.Screenshot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Login Test Class
 * 
 * <P>
 * All tests for the login class are implemented here
 * <P>
 * 
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class LoginTests {

	ExtentReports extent;
	ExtentTest test;

	ReadXMLData readXml = new ReadXMLData(
			"\\myPractice\\src\\test\\java\\com\\myPractice\\demo\\regression//loginTests//TestData.xml");
	// TODO
	// String xmlUserName = readXml.getData("data.UserName");
	// String xmlPassword = readXml.getData("data.Password");

	public WebDriver d = null;
	String baseURL = "";
	Base b = new Base();

	@BeforeSuite
	void suiteSetUp() {

		baseURL = "https://www.leagueplanit.com";
	}

	@AfterSuite
	void suiteCleanUp() {
		if (d != null) {
			d.quit();
		}
	}

	@BeforeTest
	public void init() {

		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentScreenshot.html");
	}

	@AfterTest
	public void endReport() {

		extent.flush();
		extent.close();
	}

	@BeforeMethod
	void setUp() {

		d = b.getDriver("Chrome");
		d.get(baseURL);
		// d.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

	}

	/* Testing with data provider */

	@Test(dataProvider = "ValidNamesProvider", dataProviderClass = LoginDataProvider.class)

	public void testValidUserNamePasswd(String validName, String validPassword) throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		LeaguePage lp = null;

		boolean isLeaguePageLoaded = false;
		lp = loginPage.login(validName, validPassword);
		isLeaguePageLoaded = lp.isLeaguePage(d);

		Assert.assertTrue(isLeaguePageLoaded, "League page not loaded");
	}

	@Test

	public void testInvalidPassword() {

		test = extent.startTest("testInvalidPassword");
		System.out.println("Time test started: " + test.getStartedTime());

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badPasswordString = "password";
		boolean isPasswordinMsg = false;

		loginPage.login("Username", "invalidPassword");
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isPasswordinMsg = StringUtils.containsIgnoreCase(errorMessageString, badPasswordString);

		/* Uncomment this to simulate a failure */
		// isPasswordinMsg = false;

		Assert.assertTrue(isPasswordinMsg, "Correct message is not displayed for incorrect password");
		test.log(LogStatus.PASS, "Correct message is displayed for incorrect password");
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshotPath = Screenshot.capture(d, "screenshot");
			// test.log(LogStatus.FAIL, result.getThrowable());
			// test.log(LogStatus.FAIL, "Screenshot Below: " +
			// test.addScreenCapture(screenshotPath));
			System.out.println(screenshotPath);
		}

		if (d != null) {
			d.quit();
		}
		extent.endTest(test);
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

	/* Testing with parameters */

	@Test
	@Parameters({ "name", "password" })

	public void testParamInvalidUsrNmPassWd(String name, String password) throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badUserNameString = "username";
		String badPasswordString = "password";
		boolean isUserNameinMsg = false;
		boolean isPasswordinMsg = false;
		boolean isUserNameAndPassWdinMsg = false;

		loginPage.login(name, password);
		errorMessageString = loginPage.getBadNamePassWordMsg().getText();
		isUserNameinMsg = StringUtils.containsIgnoreCase(errorMessageString, badUserNameString);
		isPasswordinMsg = StringUtils.containsIgnoreCase(errorMessageString, badPasswordString);

		// Make sure both user name and password are mentioned in the error
		// message
		isUserNameAndPassWdinMsg = (isUserNameinMsg) && (isPasswordinMsg);
		Assert.assertTrue(isUserNameAndPassWdinMsg,
				"Correct message is not displayed for incorrect user name and password");
	}

	/* Testing with data provider */

	@Test(dataProvider = "SearchProvider", dataProviderClass = LoginDataProvider.class)

	public void testDataProviderInvalidUsrNmPassWd(String name, String password) throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);

		String errorMessageString = "";
		String badUserNameString = "username";
		String badPasswordString = "password";
		boolean isUserNameinMsg = false;
		boolean isPasswordinMsg = false;
		boolean isUserNameAndPassWdinMsg = false;

		loginPage.login(name, password);
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

	/* Tests to access other pages */

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

		boolean isForgotPasswordPageLoaded = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		ForgotPasswordPage fp = loginPage.clickLnkResetPassword();
		isForgotPasswordPageLoaded = fp.isForgotPasswordPageLoaded();

		Assert.assertTrue(isForgotPasswordPageLoaded, "Create Account page not loaded");
	}

	/* Create Account page tests */

	@Test

	public void testRandCreateAccount() {

		boolean isAccountCreated = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		CreateAccountPageStep2 ca2 = ca.createRandAccountStep1();
		CreateAccountPageStep3 ca3 = ca2.createRandAccountStep2();
		LoginPage lpAfterAccountCreation = ca3.createRandAccountStep3();
		isAccountCreated = lpAfterAccountCreation.isAccountCreated();

		Assert.assertTrue(isAccountCreated, "Account not created successfully");
	}

	@Test

	public void testCreateAccount() {

		boolean isAccountCreated = false;

		try {
			LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
			CreateAccountPage ca = loginPage.createAccount();
			CreateAccountPageStep2 ca2 = ca.createAccountStep1("himanshu", "keskar", "himanshu.keskar@gmail.com", 2, 2);
			CreateAccountPageStep3 ca3 = ca2.createRandAccountStep2();
			LoginPage lpAfterAccountCreation = ca3.createRandAccountStep3();
			isAccountCreated = lpAfterAccountCreation.isAccountCreated();
			Assert.assertTrue(isAccountCreated, "Account not created successfully");
		} finally {
			d.quit();
			System.exit(0);
		}

	}

	/* Forgot Password page tests */

	@Test

	public void testPasswordChanged() {

		boolean isPasswordChanged = false;

		try {
			LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
			ForgotPasswordPage fp = loginPage.clickLnkResetPassword();
			ResetPasswordPage rp = fp.resetPasswordStep1();
			LoginPage lpAfterPasswordReset = rp.resetPasswordStep2();
			isPasswordChanged = lpAfterPasswordReset.isPasswordUpdated();
			Assert.assertTrue(isPasswordChanged, "Password not changed successfully");
		} finally {
			// driver.quit();
		}
	}

	// TODO

	/* Testing with data from XML file */

	/*
	 * 
	 * @Test
	 * 
	 * public void testXMLValidUserNamePasswd(){
	 * 
	 * //System.out.println(xmlUserName); //System.out.println(xmlPassword);
	 * 
	 * LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
	 * LeaguePage lp = null;
	 * 
	 * boolean isLeaguePageLoaded = false; lp = loginPage.login("a", "b");
	 * isLeaguePageLoaded = lp.isLeaguePage(d);
	 * 
	 * Assert.assertTrue(isLeaguePageLoaded, "League page not loaded"); }
	 * 
	 */

}
