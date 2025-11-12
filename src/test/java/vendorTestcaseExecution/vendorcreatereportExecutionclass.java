package vendorTestcaseExecution;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import Basepackage.vendorBaseclass;
import vendorpageobject.vendorcreatereportpage;

public class vendorcreatereportExecutionclass extends vendorBaseclass {
	
	@Test
	public void createreport() throws InterruptedException
	{
		vendorcreatereportpage createreport = new vendorcreatereportpage(driver);
		createreport.createreportcontract(prop.getProperty("vendoremail"), prop.getProperty("password"),
				prop.getProperty("QTyinputdata"), prop.getProperty("percentageinputdata"), prop.getProperty("noteinputfield"));
	}

}
