package com.gojek.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.gojek.pageObjects.DepartmentsSectionPage;
import com.gojek.pageObjects.SignInPage;
import com.gojek.utilities.BaseClass;

public class test extends BaseClass{
	
	SignInPage signinpage;
	DepartmentsSectionPage dsp;
	
	@Test()
	public void test() throws IOException {
		openBrowser();  
		signinpage.clicksignInNavButton();
		dsp.test();		
	}
	

}
