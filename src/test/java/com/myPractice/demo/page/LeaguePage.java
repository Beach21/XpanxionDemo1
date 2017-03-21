package com.myPractice.demo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * League Page Object
 * 
 * <P>
 * User comes here to after logging in from the login page
 * <P>
 * Suitable locators, methods associated with this Page Object defined here
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class LeaguePage {

	/** Variables and constants */
	final WebDriver driver;

	/** Locators */
	@FindBy(how = How.XPATH, using = "//a[@title='Join a team']")
	public WebElement btnJoinTeam;

	/** Methods */
	public LeaguePage(WebDriver driver) {

		this.driver = driver;
	}

	public String getPageURL() {

		return driver.getCurrentUrl();
	}

	public boolean isLeaguePageLoaded(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		String aboutURLString = "/league/index";

		if (currentURL.contains(aboutURLString)) {
			return true;
		}

		else {
			System.out.println("Not reached 'League' page");
			System.out.println("About URL substring is:  " + aboutURLString);
			System.out.println("Current page is: " + currentURL);
			return false;
		}
	}

	public WebElement getBtnJoinTeam(WebDriver driver) {

		try {
			WebElement we = btnJoinTeam;
		} catch (Exception e) {

			e.getMessage();
		}

		return btnJoinTeam;
	}
	
	public boolean isLeaguePage(WebDriver driver){
		
		WebElement we = getBtnJoinTeam(driver);
		
		if (we != null){
			return true;
		}
		else {
			return false;
		}
	}

}
