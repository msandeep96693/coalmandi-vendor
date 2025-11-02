package vendorTestcaseExecution;

import org.testng.annotations.Test;

import Basepackage.vendorBaseclass;
import vendorpageobject.vendorQAreporthomepage;

public class vendorQAreportlistExecutionclass extends vendorBaseclass {
	
	@Test
	public void vendorQAreportlistpage() throws InterruptedException
	{
		vendorQAreporthomepage homepage = new vendorQAreporthomepage(driver);
		homepage.vendorQAreportlistpage(prop.getProperty("vendoremail"),prop.getProperty("password"),prop.getProperty("searchreportID"),
				prop.getProperty("reportstatusname"));
	}

}
