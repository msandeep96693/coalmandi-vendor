package vendorTestcaseExecution;

import org.testng.annotations.Test;
import java.awt.AWTException;

import org.testng.annotations.Test;


import Basepackage.vendorBaseclass;
import vendorpageobject.vendorBusinesscreationpage;

public class vendorcreatebusinessprofileExecutionclass extends vendorBaseclass {

	//working
	@Test(priority = 0, enabled = true)
	public void vendorcreatebusinessprofileExecutionclass() throws InterruptedException, AWTException
	{
		vendorcreate = new vendorBusinesscreationpage(driver);
		vendorcreate.vendorcreatebbusinessprofile(prop.getProperty("vendoremail"),prop.getProperty("password"),
				prop.getProperty("mobilenumber"), prop.getProperty("createpwd"),
				prop.getProperty("confirmpwd"),
				 prop.getProperty("gstnumber"), prop.getProperty("designation"), prop.getProperty("IFSCcode"), prop.getProperty("accountno"),
				 prop.getProperty("confirmaccountno"),prop.getProperty("udyamno"), prop.getProperty("udyamdateno"),
				 prop.getProperty("iecdateno"), prop.getProperty("DnBno")
				 
				);		
		  	
	}
}
