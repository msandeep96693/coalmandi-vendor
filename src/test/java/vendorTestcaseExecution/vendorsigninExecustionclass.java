package vendorTestcaseExecution;

import org.testng.annotations.Test;


import Basepackage.vendorBaseclass;
import vendorpageobject.vendorsigninpage;

public class vendorsigninExecustionclass extends vendorBaseclass {
	
	@Test
	public void opssignin() throws InterruptedException
	{
		vendorsign = new vendorsigninpage(driver);
		vendorsign.vendorsignin(prop.getProperty("vendoremail"), prop.getProperty("password"));
	}
 
}
