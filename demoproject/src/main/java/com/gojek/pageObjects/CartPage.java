package com.gojek.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.Config;
import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class CartPage {
	WebDriver driver;
	ReusableMethods reuse;

	@FindBy(xpath = "(//input[@value='Delete'])[2]")
	WebElement button_DeleteCartItem;

	@FindBy(xpath = "//i[@class='a-icon a-icon-dropdown']")
	WebElement quantityDropDwn;

	@FindBy(xpath = "(//li[@tabindex='0']/a)[1]")
	WebElement quantityOneItemSelect;

	@FindBy(name = "proceedToCheckout")
	WebElement button_ProceedToCheckout;

	@FindBy(xpath = "//h1[text()='Select a delivery address']") 
	WebElement lable_SelectDelAddress;
	
	public CartPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(button_DeleteCartItem));  
		reuse = ReusableMethods.getInstance(driver);
	}
	
	public void verifyCartPage() {
		if(button_DeleteCartItem.isDisplayed())
			Log.pass("Cart page is displayed, items can be modified");
		else
			Log.fail("Cart page is not displayed, items cannot be modified");  				
	}
	 
	public LogOutPage deleteCartItem() { 
		try {
			reuse.clickElement(button_DeleteCartItem); 
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", quantityDropDwn);  
//			reuse.clickElement(quantityDropDwn);
			reuse.clickElement(quantityOneItemSelect); 
			reuse.clickElement(button_ProceedToCheckout); 
			if(lable_SelectDelAddress.isDisplayed()) 
				Log.pass("Delivery address details page is displayed"); 
			else
				Log.fail("Delivery address details page is not displayed");
			driver.get(Config.getProperty("URL:")); 
			return new LogOutPage(driver);
		} catch (Exception e) {
			Log.fail("deleteCartItem is failed"); 
			e.printStackTrace();
		}
		return null;  
	}	
}
