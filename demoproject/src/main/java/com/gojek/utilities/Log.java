package com.gojek.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class Log extends BaseClass {

	public Log() {
		super();
	}

	static boolean isPassed = true;
	int count = 0;
	static DateFormat dateFormat = new SimpleDateFormat("DDMMYYYY");
	static Calendar cal = Calendar.getInstance();
	public static String htmlfile = Config.getProperty("HTMLReportPath:") + "//" + dateFormat.format(cal.getTime())
			+ "/report.html";
	private static Logger log = Logger.getLogger("test");

	@SuppressWarnings("deprecation")
	public static void startTestCase() {
		report = new ExtentReports(htmlfile, false);
		report.config().documentTitle("Automation Report").reportName("GoJek prob statement")
				.reportHeadline("test case report");
		reportCreated = true;
		test = report.startTest(TestCaseName);
	}

	@AfterClass
	public static void endTestCase() {
		report.endTest(test);
		report.flush();
		if (!isPassed) {
			isPassed = true;
			Assert.assertTrue(false);
		}
	}

	public static void pass(String message) {
		log.info(message);
		test.log(LogStatus.PASS, message);
		getScreenshot("Screenshoot");
	}

	public static void fail(String message) {
		isPassed = false;
		log.error(message);
		test.log(LogStatus.FAIL, message);
		getScreenshot("Failed Screenshoot");
		driver.close();
		endTestCase();
	}
	
	public static void error(String message) {
		isPassed = false;
		log.error(message);
		test.log(LogStatus.ERROR, message);
		getScreenshot("Failed Screenshoot");
		driver.close();
		Assert.assertTrue(false);  
	}
}
