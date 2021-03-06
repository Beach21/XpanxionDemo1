package com.myPractice.demo.utilClasses;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


/**
 * Read XMl Data
 * 
 * <P>
 * Screenshot.java
 * 
 * Has methods that are used to capture screen shots
 * 
 * <P>
 * 
 * 
 * @author himanshu.keskar@gmail.com
 * @version 1.0
 */
public class Screenshot {

		public static String capture(WebDriver driver, String screenshotName) throws IOException{
			
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String dest = "C:\\Work\\screenshot.png";
			File destination = new File(dest);			
			FileUtils.copyFile(source, destination);
			
			return dest;
		}
}
