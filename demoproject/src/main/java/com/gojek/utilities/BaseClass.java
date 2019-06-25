package com.gojek.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	
	protected BaseClass() {
	}

	public static WebDriver driver = null;
	public static boolean exe = true;
	public static int Execution = 0;
	public static int trip = 1;
	public static ExtentReports report;
	public static ExtentTest test;
	public static boolean reportCreated = false;
	public static String TestCaseName = null;  
	public static DateFormat dateFormat = new SimpleDateFormat("DDMMYYYY");
	public static Calendar cal = Calendar.getInstance();

	public void openBrowser() throws IOException {

		String browser = Config.getProperty("Broswer:");
		if (browser.equals("Specified in config sheet")) {
			String className = this.getClass().getSimpleName();
			String packageName = this.getClass().getName().split("\\.")[1];
			browser = ReadExcel.data(packageName, className, "Browser"); 
		}
		if (browser.equalsIgnoreCase("chrome")) {
			String path = Config.getProperty("ChromePath:");
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			String path = Config.getProperty("FirefoxPath:");
			System.setProperty("webdriver.gecko.driver", path);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			String path = Config.getProperty("IEPath:");
			System.setProperty("webdriver.ie.driver", path);
			driver = new ChromeDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(Config.getProperty("URL:"));  
	}
	
	public String getData(String columnName) {
		String className = this.getClass().getSimpleName();
		String packageName = this.getClass().getName().split("\\.")[1];
		return ReadExcel.data(packageName, className, columnName);		
	}
	
	@SuppressWarnings("unused")
	public static void getScreenshot(String description) {
		try {
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File scrshot = new File((Config.getProperty("HTMLReportPath:")+"//"+dateFormat.format(cal.getTime())+"\\screenshots\\"+".jpg"));
			FileUtils.copyFile(scrFile, scrshot);
			String path = scrshot.getAbsolutePath();
			test.log(LogStatus.INFO, description+":" +test.addScreenCapture("\\screenshots\\"+".jpg")); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void startLog() {
		TestCaseName = this.getClass().getSimpleName();
		Log.startTestCase();
	}

	public void endLog() {
		Log.endTestCase();
		driver.close();
	}

}
