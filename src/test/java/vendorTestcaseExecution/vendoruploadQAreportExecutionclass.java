package vendorTestcaseExecution;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import Basepackage.vendorBaseclass;
import vendorpageobject.vendorcontractQAreportpage;

public class vendoruploadQAreportExecutionclass extends vendorBaseclass {

	// working
	@Test(priority = 1, enabled = true)
	public void QAreportuploadpage() throws InterruptedException
	{
		vendorcontractQAreportpage QAreport = new vendorcontractQAreportpage(driver);
		QAreport.createQAreport(prop.getProperty("vendoremail"), prop.getProperty("password"), 
				prop.getProperty("percentageinputdata"),prop.getProperty("noteinputfield"));
	}
	
	
}
