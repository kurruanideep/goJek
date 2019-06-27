package com.gojek.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.Config;
import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class SignInPage {
	WebDriver driver;
	ReusableMethods reuse;
	
	@FindBy(id = "nav-link-accountList")
	WebElement signInNavButton;
	
	@FindBy(id = "ap_email")
	WebElement txtBox_Username;
	
	@FindBy(id = "continue")
	WebElement btn_Continue;
	
	@FindBy(id = "ap_password")
	WebElement txtBox_Password;
	
	@FindBy(id = "signInSubmit")
	WebElement btn_SignIn;
	
	public SignInPage(WebDriver driver) {  
		this.driver=driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(signInNavButton));
		reuse = ReusableMethods.getInstance(driver);  
	}
	
	public void clicksignInNavButton() throws IOException {
		try {
			reuse.clickElement(signInNavButton);  
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

	public DepartmentsSelectionPage login() throws IOException {  
		try {
			reuse.sendText(txtBox_Username, Config.getProperty("UserName:")); 
			reuse.clickElement(btn_Continue);  
			reuse.sendText(txtBox_Password, Config.getProperty("Password:"));  
			reuse.clickElement(btn_SignIn);
			Log.pass("est");
//			return new DepartmentsSelectionPage(driver); 
		} catch (Exception e) {
			Log.fail("login is failed");
			e.printStackTrace();
		}
		return null;     
	}
}
