package com.myPractice.demo.page;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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
	
	@FindBy(how = How.ID, using = "email_id")
	public WebElement txtBx_EmailID;
	
	@FindBy(how = How.ID, using = "SecurityQuestionID")
	public WebElement drpDwn_SecurityQuestion;
	
	@FindBy(how = How.XPATH, using = "//Select[@id='SecurityQuestionID']/option")
	public List<WebElement> securityQuestions;	
	
	@FindBy(how = How.ID, using = "SecurityQuestionAnswer")
	public WebElement txtBx_SecurityQuestionAnswer;
	
	@FindBy(how = How.ID, using = "btn_Submit")
	public WebElement btn_Go;

	/** Constructor */
	public ForgotPasswordPage(WebDriver driver) {

		this.driver = driver;
	}

	/** Methods */
	public String getPageURL() {

		return driver.getCurrentUrl();
	}
	
	public WebElement getTxtBxUserName() {

		return txtBx_EmailID;
	}
	
	WebElement getDrpdwnSecurityQuestion() {

		return drpDwn_SecurityQuestion;
	}
	
	List<WebElement> getSecurityQuestions() {

		return securityQuestions;
	}


	WebElement getTxtBxAnswer() {

		return txtBx_SecurityQuestionAnswer;
	}
	
	WebElement getBtnGo() {

		return btn_Go;
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
	
	public ResetPasswordPage resetPasswordStep1() {

		fillUserName();
		fillSecurityQuestion();		
		fillAnswer();		
		clickBtnGo();

		return PageFactory.initElements(driver, ResetPasswordPage.class);

	}

	public void fillUserName() {

		WebElement fp = getTxtBxUserName();
		fp.sendKeys("himkes@gmail.com");		
	}
	
	public void fillSecurityQuestion() {

		WebElement sq = getDrpdwnSecurityQuestion();
		sq.click();	
		List<WebElement> sq1 = getSecurityQuestions();
		sq1.get(1).click();
	}
	
	public void fillAnswer() {

		WebElement fp = getTxtBxAnswer();
		fp.sendKeys("pinku");
		fp.sendKeys(Keys.RETURN);
	}
	
	public void clickBtnGo() {

		WebElement fp = btn_Go;
		fp.click();	
	}


}
