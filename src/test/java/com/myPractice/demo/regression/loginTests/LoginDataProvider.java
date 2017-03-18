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
			
			{ "username1", "password1" },
			{ "uname", "pword" },
			{ "UserName", "Password" } 
		};
	}
}