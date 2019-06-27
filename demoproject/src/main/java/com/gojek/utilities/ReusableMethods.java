package com.gojek.utilities;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods extends BaseClass {

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

	public void windowHandle(WebElement element) throws IOException, InterruptedException {
		String parentWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String childWindow = i1.next();
			if (!parentWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);  
				reuse.clickElement(element);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
	}

	public boolean selectDropdownByVisibleText(WebElement element, String text) throws IOException {
		int i = 0;
		try {
			for (i = 0; i < 7; i++) {
				try {
					if (element.isDisplayed() && element.isEnabled()) {
						Select elementSelectObj = new Select(element);
						elementSelectObj.selectByVisibleText(text);
						break;
					} else
						continue;
				} catch (TimeoutException e) {
					Log.fail("Element is not selected");
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void clickElement(WebElement element) throws IOException {
		int i = 0;
		for (i = 0; i < 7; i++) {
			try {
				(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(element));
				if (element.isDisplayed() && element.isEnabled()) {
					element.click();
					break;
				}
			} catch (WebDriverException e) {
				Log.fail("Click element is failed");
				continue;
			}
		}
	}

	public boolean sendText(WebElement element, String text) throws IOException {
		int i = 0;
		try {
			for (i = 0; i < 7; i++) {
				try {
					if (element.isDisplayed() && element.isEnabled()) {
						element.clear();
						element.sendKeys(text);
						if (!element.getAttribute("value").trim().equalsIgnoreCase(text.trim())) {
							continue;
						}
						return true;
					} else
						continue;
				} catch (TimeoutException e) {
					Log.fail("Send text is failed");
					continue;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
