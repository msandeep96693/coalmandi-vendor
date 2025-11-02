package vendorpageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class vendorcontractQAreportpage  extends vendorBasicpage {

	public vendorcontractQAreportpage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//button[.='Contracts']")
	private WebElement leftnavcontractbutton;
	
	@FindBy(xpath = "//div[@class='ant-card-body']")
	private List<WebElement> contractlistdata;
	
	@FindBy(xpath = "//button[.='View Details']")
	private List<WebElement> listofviewdetailsbutton;
	
	@FindBy(xpath = "//button[.='Upload Report']")
	private WebElement uploadreportbutton;
	
	@FindBy(xpath = "//label[@class='ant-form-item-required']")
	private List<WebElement> listoflabeltextfield;
	
	@FindBy(xpath = "(//input[@type='number'])[1]")
	private WebElement QTYfield;
	
	@FindBy(xpath = "(//input[@type='number'])[2]")
	private WebElement carbonfield;
	
	@FindBy(xpath = "(//input[@type='number'])[3]")
	private WebElement ashcontentfield;
	
	@FindBy(xpath = "(//input[@type='number'])[4]")
	private WebElement volatilefield;
	
	@FindBy(xpath = "(//input[@type='number'])[5]")
	private WebElement moisturefield;
	
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
	
	
	public void createQAreport(String email, String pwd, String statusoptionname,
			 String QTYinputdata,String percentageinputdata, String noteinputfield
			
			) throws InterruptedException
	{
		vendorsigninpage signin =  new vendorsigninpage(driver);
		signin.vendorsignin(email, pwd);
		
		// click on contract nav bar
		waitforElement(leftnavcontractbutton);
		javascriptclick(leftnavcontractbutton);
		
		// click on the view details page based on a status
				for(int i = 0; i<contractlistdata.size(); i++)
				{
						String contractdata = contractlistdata.get(i).getText().trim();
						System.out.println("contract data :- "+ contractdata);
						if(contractdata.contains(statusoptionname))
						{
							clickonviewdetailsbutton();
							break;
						}		
				}
				
				
		// cross check report form getting current url 	
		String reportformurl = driver.getCurrentUrl();
		System.out.println("Report Form URL :- "+reportformurl);
		
		// click on upload report button
		waitforElement(uploadreportbutton);
		javascriptclick(uploadreportbutton);
		
		
		// Enter the data into input fields
		waitforElement(QTYfield);
		QTYfield.sendKeys(QTYinputdata);
		
		waitforElement(carbonfield);
		carbonfield.sendKeys(percentageinputdata);
		
		waitforElement(ashcontentfield);
		ashcontentfield.sendKeys(percentageinputdata);
		
		waitforElement(volatilefield);
		volatilefield.sendKeys(percentageinputdata);
		
		waitforElement(moisturefield);
		moisturefield.sendKeys(percentageinputdata);
		
		
		
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
	
	public void clickonviewdetailsbutton()
	{
		for(int i = 0; i<= listofviewdetailsbutton.size(); i++)
		{
			listofviewdetailsbutton.get(i).click();
			break;
		}
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
