package vendorTestcaseExecution;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import Basepackage.vendorBaseclass;
import vendorpageobject.vendorcontracthomepage;

public class vendorcontractlistExecutionclass extends vendorBaseclass {
	
	@Test(priority = 0, enabled = true)
	public void contractlistpage() throws InterruptedException
	{
		vendorcontracthomepage homepage = new vendorcontracthomepage(driver);
		homepage.contractlistfunctionality(prop.getProperty("vendoremail"),prop.getProperty("password"),
				prop.getProperty("searchbusinessname"));
	}

}
