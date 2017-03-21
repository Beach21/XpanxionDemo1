package com.myPractice.demo.regression.loginTests;

import org.testng.annotations.DataProvider;

/**
 * Login Page Object
 * 
 * <P>
 * This is the data provider for the login tests
 * <P>
 * This has data that can be used for multiple runs of the test
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */

public class LoginDataProvider {

	@DataProvider(name = "SearchProvider")
	public static Object[][] getDataFromDataprovider() {
		return new Object[][] {

				{ "username1", "password1" }, { "uname", "pword" }, { "UserName", "Password" } };
	}

	@DataProvider(name = "ValidNamesProvider")
	public static Object[][] getValidNamesFromDataprovider() {
		return new Object[][] { 
			
			{ "himkes@gmail.com", "pinku" },
			{ "himkes1@gmail.com", "pinku1" },
			{ "himkes4@gmail.com", "pinku4" } 
		};
	}
}