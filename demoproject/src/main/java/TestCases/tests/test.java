package TestCases.tests;

import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gojek.pageObjects.CartPage;
import com.gojek.pageObjects.DepartmentsSelectionPage;
import com.gojek.pageObjects.LogOutPage;
import com.gojek.pageObjects.SignInPage;
import com.gojek.utilities.BaseClass;
import com.gojek.utilities.Config;
import com.gojek.utilities.Log;

public class test extends BaseClass {

	@BeforeClass
	public void start() throws Exception {
		getData("Execution");
		if (getData("Execution").equalsIgnoreCase("Yes")) {
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
		report.flush();  
	}

	@Test(priority =1)
	public void testSignIn() throws Exception {
		SignInPage signinpage = new SignInPage(driver);
		signinpage.clicksignInNavButton();
		signinpage.login();
	}
	
	@Test(priority = 2)
	public void testAddItemToCart() throws Exception {
		DepartmentsSelectionPage deptselectionpage = new DepartmentsSelectionPage(driver);
		deptselectionpage.verifyDepartmentSelectionPage();
		deptselectionpage.addHeadPhonesToCart();
		deptselectionpage.addMacBookProToCart(getData("Item"), getData("Quantity"));
		deptselectionpage.clickCartButton();
	}
	 
	@Test(priority = 3)
	public void testDeleteItemFromCart() throws Exception {
		CartPage cartpage = new CartPage(driver);
		cartpage.verifyCartPage();
		cartpage.deleteCartItem();
	} 

	@Test(priority=4) 
	public void LogOut() throws Exception {
		LogOutPage logout = new LogOutPage(driver);
		logout.clickLogOut();
	}
}
