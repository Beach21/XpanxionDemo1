package com.myPractice.demo.page;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * Create Account Page Step2 Object.
 * 
 * <P>
 * User comes here after the first page of the create account process
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class CreateAccountPageStep2 {

	/** Variables and constants */

	final WebDriver driver;

	/** Locators */

	@FindBy(how = How.XPATH, using = "//h2[@class]")
	public WebElement hdr_CreateAccount;

	@FindBy(how = How.XPATH, using = "//input[@id='Next']")
	public WebElement btn_Next;

	@FindBy(how = How.XPATH, using = "//a[contains(@class, 'btn back')]")
	public WebElement btn_GoBack;

	@FindBy(how = How.ID, using = "password")
	public WebElement txtbx_Password;

	@FindBy(how = How.ID, using = "password_conf")
	public WebElement txtbx_ConfirmPassword;

	@FindBy(how = How.ID, using = "SecurityQuestionID")
	public WebElement drpdwn_SecurityQuestion;

	@FindBy(how = How.ID, using = "SecurityQuestionAnswer")
	public WebElement txtbx_SecurityQuestionAnswer;
	
	@FindBy(how = How.XPATH, using = "//Select[@id='SecurityQuestionID']/option")
	public List<WebElement> securityQuestions;	

	@FindBy(how = How.XPATH, using = "//select[@name='TeamID']")
	public WebElement drpdwn_Team;	

	@FindBy(how = How.XPATH, using = "//Select[@name='TeamID']/option")
	public List<WebElement> teams;	

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Personal Details')]")
	public WebElement div_PersonalDetails;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Player Account')]")
	public WebElement div_PlayerAccount;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Contact')]")
	public WebElement div_Contact;

	/** Constructor */

	public CreateAccountPageStep2(WebDriver driver) {

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

	WebElement getTxtBxPassword() {

		return txtbx_Password;
	}

	WebElement getTxtBxConfirmPassword() {

		return txtbx_ConfirmPassword;
	}

	WebElement getDrpdwnSecurityQuestion() {

		return drpdwn_SecurityQuestion;
	}

	WebElement getTxtBxAnswer() {

		return txtbx_SecurityQuestionAnswer;
	}
	
	List<WebElement> getSecurityQuestions() {

		return securityQuestions;
	}

	WebElement getDrpdwnTeam() {

		return drpdwn_Team;
	}
	
	List<WebElement> getTeams() {

		return teams;
	}

	/*
	 * Checks if div_PersonalDetails is visible on the page and indirectly that
	 * the Create Account Page Step 2 is loaded
	 */
	public boolean isCreateAccountPageStep2Loaded() {

		boolean isPersonalDetails = false;
		WebElement pn = div_PersonalDetails;

		System.out.println(pn.getText());

		if (pn != null) {
			isPersonalDetails = true;
		}

		return isPersonalDetails;
	}

	public void clickNextButton() {

		try {
			btn_Next.click();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public CreateAccountPageStep3 createRandAccountStep2() {

		fillPassword();
		fillConfirmPassword();
		fillSecurityQuestion();
		fillAnswer();
		fillTeam();
		clickNextButton();

		return PageFactory.initElements(driver, CreateAccountPageStep3.class);

	}

	public void fillPassword() {

		WebElement fp = getTxtBxPassword();
		fp.sendKeys("Password");
	}

	public void fillConfirmPassword() {

		WebElement fp = getTxtBxConfirmPassword();
		fp.sendKeys("Password");
	}

	public void fillSecurityQuestion() {

		WebElement sq = getDrpdwnSecurityQuestion();
		sq.click();	
		List<WebElement> sq1 = getSecurityQuestions();
		sq1.get(2).click();
	}
	
	public void fillAnswer() {

		WebElement fp = getTxtBxAnswer();
		fp.sendKeys("pinku");
		List<WebElement> fp1 = getTeams();
		fp1.get(2).click();
	}

	public void fillTeam() {

		WebElement ft = getDrpdwnTeam();
		ft.click();
	}

}
