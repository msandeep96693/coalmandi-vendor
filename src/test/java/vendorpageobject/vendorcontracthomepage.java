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
	
	@FindBy(xpath = "//span[@aria-label='user']/..")
	private WebElement vendorprofileicon;
	
	@FindBy(xpath = "//button[.='Logout']")
	private WebElement vendorlogoutbutton;
	
	
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
						System.out.println("Executive data :- "+ contractdata);
						break;
				}
		
		waitforElement(allstatusfilter);
		allstatusfilter.click();
		
		selectDropdownOption(statusdropdownoptions, statusoptionname);
		
		// Fetch list of data based on search data
		for(int i = 0; i<=contractlistdata.size(); i++)
		{
				String contractdata = contractlistdata.get(i).getText().trim();
				if(contractdata.contains(statusoptionname))
				{
					System.out.println("Contract data :- "+ contractdata);
					break;
				}
		}
		
		waitforElement(vendorprofileicon);
		javascriptclick(vendorprofileicon);
		
		waitforElement(vendorlogoutbutton);
		javascriptclick(vendorlogoutbutton);
		
		
	}

}
