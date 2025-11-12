package vendorTestcaseExecution;

import org.testng.annotations.Test;
import java.awt.AWTException;

import org.testng.annotations.Test;


import Basepackage.vendorBaseclass;
import vendorpageobject.vendorcreateaccountpage;

public class vendorBusinessaccountExecutionclass extends vendorBaseclass {
	
	@Test(priority = 0, enabled = true)
	public void createbusinessaccountpage() throws InterruptedException
	{
		vendorcreateaccountpage account = new vendorcreateaccountpage(driver);
		account.createbusinessaccount(prop.getProperty("mobilenumber"), prop.getProperty("createpwd"), 
				prop.getProperty("confirmpwd"));
				
	}
		
}
