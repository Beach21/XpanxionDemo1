package com.myPractice.demo.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Create Account Page Step3 Object.
 * 
 * <P>
 * User comes here after the second page of the create account process
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class CreateAccountPageStep3 {

	/** Variables and constants */

	final WebDriver driver;

	/** Locators */

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Previous')]")
	public WebElement btn_Previous;

	@FindBy(how = How.XPATH, using = "//input[@id='Next']")
	public WebElement btn_CreateAccount;

	@FindBy(how = How.XPATH, using = "//div[@id='datetimepicker1']")
	public WebElement dateTimePickerBirthDate;

	@FindBy(how = How.ID, using = "PlayerBirthDate")
	public WebElement txtbx_birthDate;

	@FindBy(how = How.NAME, using = "mobile")
	public WebElement txtbx_Mobile;

	@FindBy(how = How.NAME, using = "twitter")
	public WebElement txtbx_Twitter;

	@FindBy(how = How.NAME, using = "facebook")
	public WebElement txtbx_Facebook;

	@FindBy(how = How.XPATH, using = "//div[@id='s2id_SkillLevelID']//input")
	public WebElement txtbx_SkillLevel;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Personal Details')]")
	public WebElement div_PersonalDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Player Account')]")
	public WebElement div_PlayerAccount;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Contact')]")
	public WebElement div_Contact;

	/** Constructor */

	public CreateAccountPageStep3(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */

	WebElement getHdrPersonalDetails() {

		return div_PersonalDetails;
	}

	WebElement getHdrPlayerAccount() {

		return div_PlayerAccount;
	}

	WebElement getHdrContact() {

		return div_Contact;
	}

	WebElement getTxtBxBirthdate() {

		return txtbx_birthDate;
	}

	WebElement getTxtBxMobile() {

		return txtbx_Mobile;
	}

	WebElement getTxtBxTwitter() {

		return txtbx_Twitter;
	}

	WebElement getTxtBxFacebook() {

		return txtbx_Facebook;
	}

	WebElement getTxtBxSkillLevel() {

		return txtbx_SkillLevel;
	}

	/*
	 * Checks if "Create Account" button is present on the page and indirectly
	 * that the Create Account Page Step3 is loaded
	 */
	public boolean isCreateAccountPageStep3Loaded() {

		boolean isBtnCreateAccount = false;
		WebElement pn = btn_CreateAccount;

		System.out.println(pn.getText());

		if (pn != null) {
			isBtnCreateAccount = true;
		}

		return isBtnCreateAccount;
	}

	public void clickCreateAccountButton() {

		try {
			btn_CreateAccount.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public LoginPage createRandAccountStep3() {

		fillBirthDate();
		fillMobile();
		fillTwitter();
		fillFaceBook();
		fillSkillLevel();
		clickCreateAccountButton();

		return PageFactory.initElements(driver, LoginPage.class);

	}

	public void fillBirthDate() {

		WebElement fp = getTxtBxBirthdate();
		fp.sendKeys("03/24/1978");
		fp.click();
	}

	public void fillMobile() {

		WebElement fp = getTxtBxMobile();
		fp.sendKeys("804-212-4841");
	}

	public void fillTwitter() {

		WebElement fp = getTxtBxTwitter();
		fp.sendKeys("@hkeskar21");
	}

	public void fillFaceBook() {

		WebElement fp = getTxtBxFacebook();
		fp.sendKeys("hkesk212121");
	}

	public void fillSkillLevel() {

		WebElement fp = getTxtBxSkillLevel();
		fp.sendKeys("2.5");
		fp.sendKeys(Keys.RETURN);
	}

}
