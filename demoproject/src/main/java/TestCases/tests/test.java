package TestCases.tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gojek.pageObjects.DepartmentsSectionPage;
import com.gojek.pageObjects.SignInPage;
import com.gojek.utilities.BaseClass;
import com.gojek.utilities.Config;
import com.gojek.utilities.Log;

public class test extends BaseClass{
	
	
	@BeforeClass
	public void start() throws IOException {
		getData("Execution");
		if(getData("Execution").equalsIgnoreCase("Yes")) {
			startLog();
			openBrowser();
		}
	}
	
	@AfterClass
	public void close() {
		endLog();
		String path = Config.getProperty("ChromePath:");
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		File file = new File(Log.htmlfile); 
		driver.get(file.getAbsolutePath());  
	}
	
	SignInPage signinpage;
	DepartmentsSectionPage dsp;
	
	@Test()
	public void test() throws IOException {
		SignInPage signinpage = new SignInPage(driver);
		signinpage.clicksignInNavButton();     
		DepartmentsSectionPage dsp = new DepartmentsSectionPage(driver);
		dsp.test();		
	}
	 

}
