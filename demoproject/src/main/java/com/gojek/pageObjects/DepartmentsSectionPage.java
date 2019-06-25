package com.gojek.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class DepartmentsSectionPage {
	
	WebDriver driver;
	ReusableMethods reuse;
	
	@FindBy(id = "nav-link-shopall")
	WebElement shopAllNavButton;
	
	@FindBy(xpath = "//a[text()='Headphones']")
	WebElement link_Headphones;
	
	@FindBy(xpath = "(//div[@class='a-row layer backGround'])[1]")
	WebElement select_Headphones;

	@FindBy(xpath="(//img[@class='s-image'])[1]")
	WebElement selectFirstResult;
	
	@FindBy(id = "add-to-cart-button")
	WebElement button_AddToCart;
	
	@FindBy(id = "twotabsearchtextbox")
	WebElement txtBox_Search;
	
	@FindBy(xpath="//input[@value='Go']")
	WebElement searchBtn;
	
	@FindBy(xpath = "(//img[@class='s-image'])[2]")
	WebElement select_SecondResult;
	
	@FindBy(name="quantity")
	WebElement selectQuantity;
	
	@FindBy(css = "span#nav-cart-count")
	WebElement button_Cart;
	
	@FindBy(xpath = "(//input[@value='Delete'])[2]")
	WebElement button_DeleteCartItem;  
	
	@FindBy(id = "a-autoid-0-announce")
	WebElement quantityDropDwn;

	@FindBy(xpath = "(//li[@tabindex='0']/a)[1]")
	WebElement quantityOneItemSelect; 
	
	@FindBy(name="proceedToCheckout")
	WebElement button_ProceedToCheckout;
	
	@FindBy(xpath = "//div[@id='shipmultiplebox']//following::h1")
	WebElement lable_SelectDelAddress;

/*	public DepartmentsSectionPage(WebDriver driver) {  
		this.driver=driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(shopAllNavButton));  
		reuse = ReusableMethods.getInstance(driver);  
	}
*/
	public DepartmentsSectionPage() {
//		this.driver=driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(shopAllNavButton));  
		reuse = ReusableMethods.getInstance(driver);  
	}
	
	public void test() {
		Log.pass("pass");  
	}

	
	
	
	
	
	
	
	
	
}
