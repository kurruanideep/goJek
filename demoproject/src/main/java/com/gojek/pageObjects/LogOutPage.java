package com.gojek.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.ReusableMethods;

public class LogOutPage {
	WebDriver driver;
	ReusableMethods reuse;

	@FindBy(css = "span#nav-cart-count")
	WebElement button_Cart;

	@FindBy(xpath = "(//input[@value='Delete'])[2]")
	WebElement button_DeleteCartItem;
	
	public LogOutPage(WebDriver driver) { 
		this.driver = driver; 
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(button_Cart)); 
		reuse = ReusableMethods.getInstance(driver);
	}
} 
