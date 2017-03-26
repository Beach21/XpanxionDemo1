package com.myPractice.demo.regression.createAccountTests;

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

import com.myPractice.demo.BaseTest;
import com.myPractice.demo.page.CreateAccountPage;
import com.myPractice.demo.page.CreateAccountPageStep2;
import com.myPractice.demo.page.CreateAccountPageStep3;
import com.myPractice.demo.page.LeaguePage;
import com.myPractice.demo.page.LoginPage;
import com.myPractice.demo.utilClasses.Screenshot;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Create Accounts Test Class
 * 
 * <P>
 * All tests for creating account are implemented here
 * <P>
 * 
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class CreateAccountTests extends BaseTest {

	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;

	public WebDriver d = null;
	String baseURL = "";
	BaseTest b = new BaseTest();

	@BeforeSuite
	void suiteSetUp() {

		baseURL = super.baseURL;
	}

	@AfterSuite
	void suiteCleanUp() {
		if (d != null) {
			d.quit();
		}
	}

	@BeforeTest
	public void init() {

		extent = new ExtentReports(System.getProperty("user.dir") + super.extentScreenshotLocation);
	}

	@AfterTest
	public void endReport() {

		extent.flush();
		extent.close();
		d.quit();
	}

	@BeforeMethod
	void setUp() {

		d = super.getDriver();
		d.get(baseURL);
		d.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@AfterMethod
	void cleanUp() {

		d.quit();
	}

	/* Testing with data provider */

	@Test(dataProvider = "ValidNamesProvider", dataProviderClass = CreateAccountDataProvider.class)

	public void testValidUserNamePasswd(String validName, String validPassword) throws InterruptedException {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		LeaguePage lp = null;

		boolean isLeaguePageLoaded = false;
		lp = loginPage.login(validName, validPassword);
		isLeaguePageLoaded = lp.isLeaguePage(d);

		Assert.assertTrue(isLeaguePageLoaded, "League page not loaded");
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

	@Test(dataProvider = "SearchProvider", dataProviderClass = CreateAccountDataProvider.class)

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

	public void testAccessCreateAccountPg() {

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		boolean isCreateAccountPageLoaded = false;
		CreateAccountPage ca = loginPage.createAccount();
		isCreateAccountPageLoaded = ca.isCreateAccountPageLoaded();

		Assert.assertTrue(isCreateAccountPageLoaded, "Create Account page not loaded");
	}

	/* Create Account page tests */

	@Test

	public void testCreateRndmAccount() {

		boolean isAccountCreated = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		CreateAccountPageStep2 ca2 = ca.createRandAccountStep1();
		CreateAccountPageStep3 ca3 = ca2.createRandAccountStep2();
		LoginPage lpAfterAccountCreation = ca3.createRandAccountStep3();
		isAccountCreated = lpAfterAccountCreation.isAccountCreated();

		Assert.assertTrue(isAccountCreated, "Account not created successfully");
	}

	@Test(enabled = false)

	public void testCreateAccount() {

		boolean isAccountCreated = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		CreateAccountPageStep2 ca2 = ca.createAccountStep1("himanshu", "keskar", "himanshu.keskar@gmail.com", 2, 2);
		CreateAccountPageStep3 ca3 = ca2.createRandAccountStep2();
		LoginPage lpAfterAccountCreation = ca3.createRandAccountStep3();
		isAccountCreated = lpAfterAccountCreation.isAccountCreated();

		Assert.assertTrue(isAccountCreated, "Account not created successfully");

	}

	/* Invalid tests for create account */

	@Test

	public void testNoFirstNameCA() {

		boolean isCorrectErrMsg = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		ca.fillLastName();
		ca.fillEmail();
		ca.fillGender();
		ca.fillPlayerType();
		ca.clickNextButton();
		isCorrectErrMsg = ca.isFNErrorMessage();
		Assert.assertTrue(isCorrectErrMsg, "Correct error message displayed when 'First Name' field is not filled");
	}

	@Test

	public void testNoLastNameCA() {

		boolean isCorrectErrMsg = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		ca.fillFirstName();
		ca.fillEmail();
		ca.fillGender();
		ca.fillPlayerType();
		ca.clickNextButton();
		isCorrectErrMsg = ca.isLNErrorMessage();
		Assert.assertTrue(isCorrectErrMsg, "Correct error message displayed when 'Last Name' field is not filled");
	}

	@Test

	public void testNoEmailNameCA() {

		boolean isCorrectErrMsg = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		ca.fillFirstName();
		ca.fillLastName();
		ca.fillGender();
		ca.fillPlayerType();
		ca.clickNextButton();
		isCorrectErrMsg = ca.isEmailErrorMessage();
		Assert.assertTrue(isCorrectErrMsg, "Correct error message displayed when 'Email' field is not filled");
	}

	@Test

	public void testNoGenderNameCA() {

		boolean isCorrectErrMsg = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		ca.fillFirstName();
		ca.fillLastName();
		ca.fillEmail();
		ca.fillPlayerType();
		ca.clickNextButton();
		isCorrectErrMsg = ca.isGenderErrorMessage();
		Assert.assertTrue(isCorrectErrMsg, "Correct error message displayed when 'Gender' field is not filled");
	}

	@Test

	public void testNoPlayerTypeNameCA() {

		boolean isCorrectErrMsg = false;

		LoginPage loginPage = PageFactory.initElements(d, LoginPage.class);
		CreateAccountPage ca = loginPage.createAccount();
		ca.fillFirstName();
		ca.fillLastName();
		ca.fillEmail();
		ca.fillGender();
		ca.clickNextButton();
		isCorrectErrMsg = ca.isPlayerTypeMessage();
		Assert.assertTrue(isCorrectErrMsg, "Correct error message displayed when 'Player Type' field is not filled");
	}

}
