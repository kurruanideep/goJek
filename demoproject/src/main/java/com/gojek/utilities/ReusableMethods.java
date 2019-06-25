package com.gojek.utilities;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	private static ReusableMethods reuse;
	private WebDriver driver;

	public ReusableMethods(WebDriver driver) {
		this.driver = driver;
	}

	public static ReusableMethods getInstance(WebDriver driver) {
		if (reuse == null) {
			reuse = new ReusableMethods(driver);
		}
		return reuse;
	}
	
	public boolean selectDropdownByVisibleText(WebElement element, String text) throws IOException {
		int i = 0;
		try {
			for (i = 0; i < 7; i++) {
				try {
					(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(element));
					if (element.isDisplayed() && element.isEnabled()) {
						viewElement(element);
						Select elementSelectObj = new Select(element);
						elementSelectObj.selectByVisibleText(text);
						break;
					} else {
						continue;
					}
				} catch (TimeoutException e) {
					Log.error("Element is not selected");
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean clickElement(WebElement element) throws IOException {
		int i = 0;
		try {
			for (i = 0; i < 7; i++) {
				try {
					(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(element));
					viewElement(element);
					if (element.isDisplayed()) {
						element.click();
						System.out.println("Executed :clickElement, PASS");
						return true;
					} else {
						continue;
					}
				} catch (TimeoutException e) {
					Log.error("Element is not clicked");  
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public boolean viewElement(WebElement element) throws IOException, InterruptedException {

		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(3000L);
			return true;
		} catch (Throwable e) {
			return false;
		}
	}
	
	public boolean sendText(WebElement element, String text) throws IOException {
		int i = 0;
		try {
			for (i = 0; i < 7; i++) {
				try {
					(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(element));
					if (element.isDisplayed() && element.isEnabled()) {
						viewElement(element);
						element.clear();
						element.sendKeys(text);
						if (!element.getAttribute("value").trim().equalsIgnoreCase(text.trim())) {
							Thread.sleep(1000);
							continue;
						}
						System.out.println("Executed :sendText, PASS");
						return true;
					} else {
						Thread.sleep(1000);
						continue;
					}
				} catch (TimeoutException e) {
					Thread.sleep(1000);
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
