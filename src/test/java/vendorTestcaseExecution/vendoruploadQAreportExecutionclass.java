package vendorTestcaseExecution;

import org.testng.annotations.Test;

import Basepackage.vendorBaseclass;
import vendorpageobject.vendorcontractQAreportpage;

public class vendoruploadQAreportExecutionclass extends vendorBaseclass {

	@Test
	public void QAreportuploadpage() throws InterruptedException
	{
		vendorcontractQAreportpage QAreport = new vendorcontractQAreportpage(driver);
		QAreport.createQAreport(prop.getProperty("vendoremail"), prop.getProperty("password"), prop.getProperty("statusoptionname"), 
				prop.getProperty("QTyinputdata"), prop.getProperty("percentageinputdata"), 
				prop.getProperty("noteinputfield"));
	}
}
