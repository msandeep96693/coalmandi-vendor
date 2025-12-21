package vendorpageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class vendorcreatereportpage extends vendorBasicpage {

	public vendorcreatereportpage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[.='Reports']")
	private WebElement sidebarreportbtn;
	
	@FindBy(xpath = "//button[.='Create Report']")
	private WebElement createreportbtn;
	
	@FindBy(xpath = "(//span[@class='ant-select-selection-item'])[1]")
	private WebElement clickoncontractdropdown;
	
	@FindBy(xpath = "(//input[@type='search'])[1]")
	private WebElement contractsearchtextfield;
	
	@FindBy(xpath = "//div[@class='rc-virtual-list-holder']/div/div/div")
	private List<WebElement> contractdropdownoptionlist;
	
	@FindBy(xpath = "(//input[@type='number'])[1]")
	private WebElement QTYfield;
	
	@FindBy(xpath = "(//input[@type='number'])[2]")
	private WebElement carbonfield;
	
	@FindBy(xpath = "(//input[@type='number'])[4]")
	private WebElement ashcontentfield;
	
	@FindBy(xpath = "(//input[@type='number'])[6]")
	private WebElement volatilefield;
	
	@FindBy(xpath = "(//input[@type='number'])[8]")
	private WebElement moisturefield;
	
	@FindBy(xpath = "(//input[@type='number'])[3]")
	private WebElement vendorcarbonfield;
	
	@FindBy(xpath = "(//input[@type='number'])[5]")
	private WebElement vendorashfield;
	
	@FindBy(xpath = "(//input[@type='number'])[7]")
	private WebElement vendorvolatilefield;
	
	@FindBy(xpath = "(//input[@type='number'])[9]")
	private WebElement vendormoisturefield;
	
	@FindBy(xpath = "//div[@class='ant-form-item-control-input-content']/textarea")
	private WebElement notetextarea;
	
	@FindBy(xpath = "//input[@type='file']")
	private WebElement uploadfiles;
	
	@FindBy(xpath = "//button[.='Create Report']")
	private WebElement createreportbutton;
	
	@FindBy(xpath = "//div[@class='ant-card-body']")
	private List<WebElement> reportlistofdata;
	
	@FindBy(xpath = "//span[@aria-label='user']/..")
	private WebElement vendorprofileicon;
	
	@FindBy(xpath = "//button[.='Logout']")
	private WebElement vendorlogoutbutton;
	
	@FindBy(xpath = "//button[.='Reports']")
	private WebElement leftnavreportbutton;
	
	
	public void createreportcontract(String email, String pwd, String QTYinputdata,
			String percentageinputdata, String noteinputfield) throws InterruptedException
	{
		vendorsigninpage signin =  new vendorsigninpage(driver);
		signin.vendorsignin(email, pwd);
		
		// click on contract nav bar
		waitforElement(leftnavreportbutton);
		javascriptclick(leftnavreportbutton);
		
		waitforElement(createreportbtn);
		javascriptclick(createreportbtn);
		
//		waitforElement(clickoncontractdropdown);
//		javascriptclick(clickoncontractdropdown);
		
		waitforElement(contractsearchtextfield);
		contractsearchtextfield.sendKeys("266");
		
		selectDropdownOption(contractdropdownoptionlist, "266");
		
		// coal specification
//		waitforElement(carbonfield);
//		carbonfield.sendKeys(percentageinputdata);
//		
//		waitforElement(ashcontentfield);
//		ashcontentfield.sendKeys(percentageinputdata);
//		
//		waitforElement(volatilefield);
//		volatilefield.sendKeys(percentageinputdata);
//		
//		waitforElement(moisturefield);
//		moisturefield.sendKeys(percentageinputdata);
		
		// vendor coal specification
		waitforElement(vendorcarbonfield);
		vendorcarbonfield.sendKeys(percentageinputdata);
		
		waitforElement(vendorashfield);
		vendorashfield.sendKeys(percentageinputdata);
		
		waitforElement(vendorvolatilefield);
		vendorvolatilefield.sendKeys(percentageinputdata);
		
		waitforElement(vendormoisturefield);
		vendormoisturefield.sendKeys(percentageinputdata);
				
				
				
				waitforElement(notetextarea);
				notetextarea.sendKeys(noteinputfield);
				
				// upload a business profile image  // /home/active34/Downloads/photos /QA club photos/business logo.jpeg
				
				List<String> files = new ArrayList();
				files.add("C:\\Users\\User\\Desktop\\Background images\\Bg-1.jpg");
				files.add("C:\\Users\\User\\Desktop\\Background images\\Bg-2.jpg");
				files.add("C:\\Users\\User\\Desktop\\Background images\\Bg-3.jpg");

				uploadFilesMultipleTimes(files);
				
				scrollBottomofPage();
					
					waitforElement(createreportbutton);
					javascriptclick(createreportbutton);
					
					Thread.sleep(4000);
					
					
					for(int i = 0; i<=reportlistofdata.size(); i++)
					{
						String listofdata = reportlistofdata.get(0).getText().trim();
						System.out.println("Uploaded report list data :- "+ listofdata);
					}
					
					waitforElement(vendorprofileicon);
					javascriptclick(vendorprofileicon);
					
					waitforElement(vendorlogoutbutton);
					javascriptclick(vendorlogoutbutton);		
					
	}
	
	public void uploadFilesMultipleTimes(List<String> filePaths) {

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].style.display='block';", uploadfiles);

	    for (String filePath : filePaths) {
	        try {
	            uploadfiles.sendKeys(filePath);
	            System.out.println("Uploaded: " + filePath);
	        } catch (ElementNotInteractableException e) {
	            js.executeScript("arguments[0].style.display='block';", uploadfiles);
	            uploadfiles.sendKeys(filePath);
	            System.out.println("Retry Uploaded: " + filePath);
	        }
	    }
	}

}
