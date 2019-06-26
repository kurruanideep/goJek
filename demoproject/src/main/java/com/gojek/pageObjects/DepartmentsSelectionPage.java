package com.gojek.pageObjects;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

	@FindBy(id = "add-to-cart-button")
	WebElement button_AddToCart;

	@FindBy(id = "twotabsearchtextbox")
	WebElement txtBox_Search;

	@FindBy(xpath = "//input[@value='Go']")
	WebElement searchBtn;

	@FindBy(xpath = "(//img[@class='s-image'])[2]")
	WebElement select_SecondResult;

	@FindBy(id = "quantity")
	WebElement selectQuantity;

	@FindBy(id = "ap_email")
	WebElement txtBox_Username;

	public DepartmentsSelectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(shopAllNavButton));
		reuse = ReusableMethods.getInstance(driver);
	}

	public void addHeadPhonesToCart() throws IOException {
		try {
			reuse.clickElement(shopAllNavButton);
			reuse.clickElement(link_Headphones);
			reuse.clickElement(select_Headphones);
			reuse.clickElement(selectFirstResult);
			Log.pass("asdkjsa");
			reuse.windowHandle(button_AddToCart); 
		} catch (Exception e) {
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
					//((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectQuantity);
//					selectQuantity.click(); 
					Select elementSelectObj = new Select(selectQuantity);  
					elementSelectObj.selectByVisibleText(quantity);    
					//reuse.selectDropdownByVisibleText(selectQuantity, quantity);  
					/*selectQuantity.click();  
					reuse.sendText(selectQuantity, quantity); */    
					reuse.clickElement(button_AddToCart);    
					driver.close();    
				}
			}
			driver.switchTo().window(parentWindow);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
}
