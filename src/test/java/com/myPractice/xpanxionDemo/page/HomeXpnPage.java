package com.myPractice.xpanxionDemo.page;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Create Account Page Object.
 * 
 * <P>
 * User comes here from the login page when the user clicks on "Create Account"
 * link
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class HomeXpnPage {

	/** Variables and constants */

	final WebDriver driver;

	/** Locators */

	@FindBy(how = How.XPATH, using = "//h2[@class]")
	public WebElement hdr_CreateAccount;

	@FindBy(how = How.XPATH, using = "//input[@id='Next']")
	public WebElement btn_Next;

	@FindBy(how = How.ID, using = "password")
	public WebElement txtbx_Password;

	@FindBy(how = How.ID, using = "PlayerFirstName")
	public WebElement txtbx_FirstName;

	@FindBy(how = How.ID, using = "PlayerLastName")
	public WebElement txtbx_LastName;

	@FindBy(how = How.ID, using = "email")
	public WebElement txtbx_Email;

	@FindBy(how = How.ID, using = "select2-drop-mask")
	public WebElement drpDwn_Gender;

	@FindBy(how = How.ID, using = "s2id_GenderID")
	public WebElement ul_Gender;

	@FindBy(how = How.XPATH, using = "//span[@id='select2-chosen-1']")
	public WebElement drpDwn_PlayerType;

	@FindBy(how = How.XPATH, using = "//li[contains(@class, 'select')]")
	public List<WebElement> ul_GenderList;

	@FindBy(how = How.XPATH, using = "//li[contains(@class, 'select')]")
	public List<WebElement> ul_PlayerList;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'PlayerFirstName')]")
	public WebElement err_PlayerFirstName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'PlayerLastName')]")
	public WebElement err_PlayerLastName;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'email field')]")
	public WebElement err_Email;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'GenderID ')]")
	public WebElement err_Gender;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'PlayerTypeID ')]")
	public WebElement err_PlayerType;

	/** Constructor */

	public HomeXpnPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */

	/*
	 * Checks if Next button is visible on the page and indirectly that the
	 * Create Account page is loaded
	 */
	public boolean isCreateAccountPageLoaded() {

		boolean isNextButtonPresent = false;
		WebElement bn = btn_Next;

		System.out.println(bn.getText());

		if (bn != null) {
			isNextButtonPresent = true;
		}

		return isNextButtonPresent;
	}

	public void clickNextButton() {

		try {
			btn_Next.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public CreateAccountPageStep2 createRandAccountStep1() {

		fillFirstName();
		fillLastName();
		fillEmail();
		fillGender();
		fillPlayerType();
		clickNextButton();

		return PageFactory.initElements(driver, CreateAccountPageStep2.class);
	}

	public CreateAccountPageStep2 createAccountStep1(String firstName, String lastName, String email, int gender,
			int playerType) {

		fillFirstName(firstName);
		fillLastName(lastName);
		fillEmail(email);
		fillGender(gender);
		fillPlayerType(playerType);
		clickNextButton();

		return PageFactory.initElements(driver, CreateAccountPageStep2.class);
	}

	public void fillFirstName() {

		txtbx_FirstName.sendKeys(getRndFirstName());
	}

	public void fillFirstName(String firstName) {

		txtbx_FirstName.sendKeys(firstName);
	}

	public void fillLastName() {

		txtbx_LastName.sendKeys(getRndLastName());
	}

	public void fillLastName(String lastName) {

		txtbx_LastName.sendKeys(lastName);
	}

	public void fillEmail() {

		txtbx_Email.sendKeys(getRndEmail());
	}

	public void fillEmail(String email) {

		txtbx_Email.sendKeys(email);
	}

	public void fillGender() {

		ul_Gender.click();
		ul_GenderList.get(2).click();

	}

	public void fillGender(int i) {

		ul_Gender.click();
		ul_GenderList.get(i).click();

	}

	public void fillPlayerType() {

		drpDwn_PlayerType.click();
		ul_PlayerList.get(2).click();

	}

	public void fillPlayerType(int i) {

		drpDwn_PlayerType.click();
		ul_PlayerList.get(i).click();

	}

	public String getRndString(String a) {

		Random rand = new Random();

		int n = rand.nextInt(50) + 1;
		int m = rand.nextInt(50) + 1;

		return ("test" + a + n + m);
	}

	public String getRndFirstName() {

		return getRndString("FN");

	}

	public String getRndLastName() {

		return getRndString("LN");

	}

	public String getRndEmail() {

		return getRndString("LN") + "@hotmail.com";

	}

	public boolean isFNErrorMessage() {

		if (err_PlayerFirstName.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLNErrorMessage() {

		if (err_PlayerLastName.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmailErrorMessage() {

		if (err_Email.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isGenderErrorMessage() {

		if (err_Gender.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPlayerTypeMessage() {

		if (err_PlayerType.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

}
