package com.gojek.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.Config;
import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class LogOutPage {
	WebDriver driver;
	ReusableMethods reuse;

	@FindBy(css = "span#nav-cart-count")
	WebElement button_Cart;

	@FindBy(id = "nav-link-accountList")
	WebElement signInNavButton;

	@FindBy(xpath = "(//input[@value='Delete'])[1]")
	WebElement button_DeleteCartItem;
	
	@FindBy(xpath = "//span[text()='Sign Out']")
	WebElement button_SignOut; 
	
	@FindBy(id = "ap_email")
	WebElement txtBox_Username;
	

	public LogOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(signInNavButton)); 
		reuse = ReusableMethods.getInstance(driver);
	}

	public void clickLogOut() {

		try { 
			driver.get(Config.getProperty("URL:"));
			reuse.clickElement(button_Cart);
			reuse.clickElement(button_DeleteCartItem); 
			Actions action = new Actions(driver); 
			action.moveToElement(signInNavButton).build().perform();   
			reuse.clickElement(button_SignOut); 
			if(txtBox_Username.isDisplayed()) 
				Log.pass("Sign out is successfull");
			else
				Log.fail("Sign out is not successfull");  
		} catch (Exception e) { 
			Log.fail("clickLogOut is failed"); 
			e.printStackTrace();
		}
	}
}
