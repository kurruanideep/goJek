package com.gojek.pageObjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gojek.utilities.BaseClass;
import com.gojek.utilities.Log;
import com.gojek.utilities.ReusableMethods;

public class DepartmentsSelectionPage extends BaseClass { 

	WebDriver driver;
	ReusableMethods reuse;

	@FindBy(id = "nav-link-shopall")
	WebElement shopAllNavButton;

	@FindBy(xpath = "//a[text()='Headphones']")
	WebElement link_Headphones;

	@FindBy(xpath = "(//div[@class='a-row layer backGround'])[1]")
	WebElement select_Headphones;

	@FindBy(xpath = "(//img[@class='s-image'])[1]")
	WebElement selectFirstResult; 
  
	/*id = "add-to-cart-button"*/ 
	@FindBy(xpath="//button[contains(text(),'Add to Cart')]") 
	WebElement button_AddToCart;
	
	@FindBy(id = "add-to-cart-button") 
	WebElement btn_AddToCart;

	@FindBy(id = "twotabsearchtextbox")
	WebElement txtBox_Search;

	@FindBy(xpath = "//input[@value='Go']")
	WebElement searchBtn;

	@FindBy(xpath = "(//div[@class='imageContainer'])[2]") 
	WebElement select_SecondResult;

	@FindBy(xpath = "//select[@id='quantity']") 
	WebElement selectQuantity;

	@FindBy(id = "ap_email")
	WebElement txtBox_Username;
	
	@FindBy(css = "span#nav-cart-count")
	WebElement button_Cart;

	public DepartmentsSelectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(shopAllNavButton));
		reuse = ReusableMethods.getInstance(driver);
	}
	
	public CartPage clickCartButton() throws IOException {
			reuse.clickElement(button_Cart);  
			return new CartPage(driver);  
	} 

	public void verifyDepartmentSelectionPage() {
		if(shopAllNavButton.isDisplayed())  
			Log.pass("SignIn is successfull");
		else
			Log.fail("SignIn is not successfull");  				
	}
	
	public void addHeadPhonesToCart() throws IOException {
		try {
			reuse.clickElement(shopAllNavButton);
			reuse.clickElement(link_Headphones);
			reuse.clickElement(select_Headphones);
			//reuse.clickElement(selectFirstResult);
			reuse.windowHandle(button_AddToCart); 
		} catch (Exception e) {
			Log.fail("addHeadPhonesToCart is failed"); 
			e.printStackTrace();
		}
	}

	public void addMacBookProToCart(String item,String quantity) throws IOException { 
		try {
			reuse.sendText(txtBox_Search,item);  
			reuse.clickElement(searchBtn);
			reuse.clickElement(select_SecondResult);
			String parentWindow = driver.getWindowHandle();
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();

			while (i1.hasNext()) {
				String childWindow = i1.next();
				if (!parentWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
					reuse.selectDropdownByVisibleText(selectQuantity, quantity);       
					reuse.clickElement(button_AddToCart);    
					driver.close();  
					driver.switchTo().window(parentWindow);
				}
			} 
			reuse.selectDropdownByVisibleText(selectQuantity, quantity);       
			reuse.clickElement(btn_AddToCart);
		} catch (Exception e) { 
			Log.fail("addMacBookProToCart is failed"); 
			e.printStackTrace();
		}
	}
	
}
