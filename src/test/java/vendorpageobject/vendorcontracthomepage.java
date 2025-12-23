package vendorpageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class vendorcontracthomepage extends vendorBasicpage {

	public vendorcontracthomepage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[.='Contracts']")
	private WebElement leftnavcontractbutton;
	
	@FindBy(xpath = "//input[@type='text']")
	private WebElement searchtextfield;
	
	@FindBy(xpath = "//span[@title='All Status']")
	private WebElement allstatusfilter;
	
	@FindBy(xpath = "//div[@class='ant-select-item ant-select-item-option']")
	private List<WebElement> statusoptionlist;
	
	@FindBy(xpath = "//div[@class='ant-select-item ant-select-item-option']")
	private List<WebElement> statusdropdownoptions;
	
	@FindBy(xpath = "//tbody[@class='ant-table-tbody']/tr/td/span")
	private List<WebElement> contractlistdata;
	
	@FindBy(xpath = "//div[@class='ant-notification-notice-message']")
	private WebElement confirmationsuccessmessage;
	
	@FindBy(xpath = "//span[@aria-label='user']/..")
	private WebElement vendorprofileicon;
	
	@FindBy(xpath = "//button[.='Logout']")
	private WebElement vendorlogoutbutton;
	
	@FindBy(xpath = "//button[.='View Details']")
	private List<WebElement> listofviewdetailsbutton;
	
	@FindBy(xpath = "//button[.='Upload Report']")
	private WebElement uploadreportbutton;
	
	public void contractlistfunctionality(String email, String pwd, String searchbusinessname,
			String statusoptionname) throws InterruptedException
	{
		vendorsigninpage signin =  new vendorsigninpage(driver);
		signin.vendorsignin(email, pwd);
		
		// click on contract nav bar
		waitforElement(leftnavcontractbutton);
		javascriptclick(leftnavcontractbutton);
		
		// search field
		waitforElement(searchtextfield);
		searchtextfield.sendKeys(searchbusinessname);
		
		// Fetch list of data based on search data
				for(int i = 0; i<=contractlistdata.size(); i++)
				{
					String contractdata = contractlistdata.get(i).getText().trim();
					System.out.println("contract data :- "+ contractdata);
					break;
				}
		
		waitforElement(allstatusfilter);
		allstatusfilter.click();
		
		// inprogress option 
		javascriptclick(statusoptionlist.get(0));
		Thread.sleep(2000);
		
		// Fetch list of data based on status name
		for(int i = 0; i<=contractlistdata.size(); i++)
		{
			String contractdata = contractlistdata.get(i).getText().trim();
			if(contractdata.contains(statusoptionname))
			{
				System.out.println("Contract data :- "+ contractdata);
				break;
			}
		}
		
		Thread.sleep(2000);
		
		waitforElement(vendorprofileicon);
		javascriptclick(vendorprofileicon);
		
		waitforElement(vendorlogoutbutton);
		javascriptclick(vendorlogoutbutton);
		
		
	}

	public void clickonviewdetailsbutton()
	{
		for(int i = 0; i<= listofviewdetailsbutton.size(); i++)
		{
			listofviewdetailsbutton.get(i).click();
			break;
		}
	}
}
