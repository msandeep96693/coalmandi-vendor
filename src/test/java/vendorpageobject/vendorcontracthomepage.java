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
	private List<WebElement> statusdropdownoptions;
	
	@FindBy(xpath = "//div[@class='ant-card-body']")
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
		
		selectDropdownOption(statusdropdownoptions, statusoptionname);
		
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
		
		waitforElement(confirmationsuccessmessage);
		String confirmationmsg = confirmationsuccessmessage.getText();
		System.out.println("Created confirmation message :- "+ confirmationmsg);
		Thread.sleep(2000);
		
		// click on the view details page based on a status
		for(int i = 0; i<=contractlistdata.size(); i++)
		{
				String contractdata = contractlistdata.get(i).getText().trim();
				System.out.println("contract data :- "+ contractdata);
				if(contractdata.contains(statusoptionname))
				{
					clickonviewdetailsbutton();
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
