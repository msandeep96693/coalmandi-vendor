package vendorpageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class vendorQAreporthomepage extends vendorBasicpage {

	public vendorQAreporthomepage(WebDriver driver) {
		super(driver);
		}
	
	@FindBy(xpath = "//button[.='Reports']")
	private WebElement leftnavreportbutton;
	
	@FindBy(xpath = "//button[.='Create Report']")
	private WebElement createreportbutton;
	
	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchtextfield;
	
	@FindBy(xpath = "//span[@title='All Status']")
	private WebElement allstatusfilter;
	
	@FindBy(xpath = "//div[@class='ant-select-item ant-select-item-option']")
	private List<WebElement> statusdropdownoptions;
	
	@FindBy(xpath = "//div[@class='ant-card-body']")
	private List<WebElement> reportlistofdata;
	
	
	
	@FindBy(xpath = "//span[@aria-label='user']/..")
	private WebElement vendorprofileicon;
	
	@FindBy(xpath = "//button[.='Logout']")
	private WebElement vendorlogoutbutton;
	
	
	
	public void vendorQAreportlistpage(String email, String pwd, String searchreportid,
			String reportstatusoptionname) throws InterruptedException
	{
		
		vendorsigninpage signin =  new vendorsigninpage(driver);
		signin.vendorsignin(email, pwd);
		
		waitforElement(leftnavreportbutton);
		javascriptclick(leftnavreportbutton);
		
		// search field
		waitforElement(searchtextfield);
		searchtextfield.sendKeys(searchreportid);
		
		// Fetch list of data based on search data
		for(int i = 0; i<=reportlistofdata.size(); i++)
		{
			String listofdata = reportlistofdata.get(0).getText().trim();
			System.out.println("Uploaded report list data :- "+ listofdata);
			break;
		}
		
		waitforElement(allstatusfilter);
		allstatusfilter.click();
		
		selectDropdownOption(statusdropdownoptions, reportstatusoptionname);
		
		// Fetch list of data based on status name
		for(int i = 0; i<=reportlistofdata.size(); i++)
		{
				String reportdata = reportlistofdata.get(i).getText().trim();
				if(reportdata.contains(reportstatusoptionname))
				{
					System.out.println("report data :- "+ reportdata);
					break;
				}
		}
		
		Thread.sleep(2000);
		waitforElement(vendorprofileicon);
		javascriptclick(vendorprofileicon);
		
		waitforElement(vendorlogoutbutton);
		javascriptclick(vendorlogoutbutton);
	}

}
