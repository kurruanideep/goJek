package com.gojek.pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class CartPage {
	WebDriver driver;
	ReusableMethods reuse;

	@FindBy(css = "span#nav-cart-count")
	WebElement button_Cart;

	@FindBy(xpath = "(//input[@value='Delete'])[2]")
	WebElement button_DeleteCartItem;

	@FindBy(id = "a-autoid-0-announce")
	WebElement quantityDropDwn;

	@FindBy(xpath = "(//li[@tabindex='0']/a)[1]")
	WebElement quantityOneItemSelect;

	@FindBy(name = "proceedToCheckout")
	WebElement button_ProceedToCheckout;

	@FindBy(xpath = "//div[@id='shipmultiplebox']//following::h1")
	WebElement lable_SelectDelAddress;
	
	public CartPage(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(button_Cart)); 
		reuse = ReusableMethods.getInstance(driver);
	}
	
	public void deleteCartItem() { 
		try {
			reuse.clickElement(button_Cart);
			reuse.clickElement(button_DeleteCartItem); 
			reuse.clickElement(quantityDropDwn);
			reuse.clickElement(quantityOneItemSelect); 
			reuse.clickElement(button_ProceedToCheckout); 
			if(lable_SelectDelAddress.isDisplayed())
				Log.pass("Delivery address details page is displayed"); 
			else
				Log.fail("Delivery address details page is not displayed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
