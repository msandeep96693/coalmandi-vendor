package vendorpageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.interning.qual.FindDistinct;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class vendorcontractQAreportpage  extends vendorBasicpage {

	public vendorcontractQAreportpage(WebDriver driver) {
		super(driver);
	}
	
	
	// ------------ contract QA upload report xpath ---------------------

	@FindBy(xpath = "//button[.='Contracts']")
	private WebElement leftnavcontractbutton;
	
	@FindBy(xpath = "//div[@class='ant-card-body']")
	private List<WebElement> contractlistdata;
	
	@FindBy(xpath = "//button[.='View Details']")
	private List<WebElement> listofviewdetailsbutton;
	
	@FindBy(xpath = "//span[.='Upload Report']")
	private List<WebElement> uploadreportbutton;
	
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
	
	@FindBy(xpath = "//span[.='All Status']")
	private WebElement clickonstatusdropdown;
	
	@FindBy(xpath = "//div[@class='ant-select-item ant-select-item-option']")
	private List<WebElement> statusoptionlist;
	
	@FindBy(xpath = "//button[.='View Detai']")
	private List<WebElement> viewdetailsbtn;
	
	@FindBy(xpath = "//span[.='View Report']")
	private List<WebElement> viewreportbtn;
	
	@FindBy(xpath = "//span[.='Upload Report' or .='View Report']")
	private List<WebElement> reportButtons;
	
	
	public void createQAreport(String email, String pwd,
			 String percentageinputdata, String noteinputfield
			
			) throws InterruptedException
	{
		vendorsigninpage signin =  new vendorsigninpage(driver);
		signin.vendorsignin(email, pwd);
		
		// click on contract nav bar
		waitforElement(leftnavcontractbutton);
		javascriptclick(leftnavcontractbutton);
		
		waitforElement(clickonstatusdropdown);
		clickonstatusdropdown.click();
		
		// inprogress option 
		javascriptclick(statusoptionlist.get(0));
		Thread.sleep(2000);
		 
		// click on view details
		javascriptclick(viewdetailsbtn.get(0));
		
		scrollBottomofPage();
		
		// create QA Report

		// with private mine coal data - listing coal spec and vendor coal specification
		handleReportButtons1(percentageinputdata);
		
//		// without private mine coal data - only vendor coal specification
//		handleReportButtons2(percentageinputdata);

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
	
	public void handleReportButtons1(String percentageinputdata) throws InterruptedException 
	{
		boolean shouldLogout = false;	
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By uploadBtnBy = By.xpath("//span[.='Upload Report']");
	    By allReportBtnBy = By.xpath("//span[.='Upload Report' or .='View Report']");

	    while (true) {

	        // 🔁 Always re-fetch
	        List<WebElement> uploadButtons = driver.findElements(uploadBtnBy);

	        // ✅ BREAK CONDITION — no Upload Report available
	        if (uploadButtons.isEmpty()) {
	            System.out.println("ℹ️ No Upload Report button available. Exiting loop.");
	            shouldLogout = true;   // 👈 mark for logout
	            break;   
	        }

	        WebElement btn = uploadButtons.get(0); // SAFE

	        // ✅ Scroll to center
	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                btn
	        );

	        // ✅ Click Upload Report
	        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
	        Thread.sleep(500);

	        // ---------- YOUR EXISTING LOGIC ----------
	        waitforElement(vendorcarbonfield);
	        vendorcarbonfield.sendKeys(percentageinputdata);

	        waitforElement(vendorashfield);
	        vendorashfield.sendKeys(percentageinputdata);

	        waitforElement(vendorvolatilefield);
	        vendorvolatilefield.sendKeys(percentageinputdata);

	        waitforElement(vendormoisturefield);
	        vendormoisturefield.sendKeys(percentageinputdata);

	        waitforElement(notetextarea);
	        notetextarea.sendKeys("QA report uploaded with qty");

	        List<String> files = new ArrayList<>();
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");

	        uploadFilesMultipleTimes(files);

	        scrollBottomofPage();

	        waitforElement(createreportbutton);
	        javascriptclick(createreportbutton);

	        Thread.sleep(1500);

	        System.out.println("✅ One QA report uploaded successfully");

	        // Loop continues → checks again if Upload Report exists
	        
	        if (shouldLogout) {
	            System.out.println("🔐 Logging out vendor after loop completion");

	            waitforElement(vendorprofileicon);
	            javascriptclick(vendorprofileicon);

	            waitforElement(vendorlogoutbutton);
	            javascriptclick(vendorlogoutbutton);
	        }

	    }
	}

	public void handleReportButtons2(String percentageinputdata) throws InterruptedException 
	{
		boolean shouldLogout = false;	
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    By uploadBtnBy = By.xpath("//span[.='Upload Report']");
	    By allReportBtnBy = By.xpath("//span[.='Upload Report' or .='View Report']");

	    while (true) {

	        // 🔁 Always re-fetch
	        List<WebElement> uploadButtons = driver.findElements(uploadBtnBy);

	        // ✅ BREAK CONDITION — no Upload Report available
	        if (uploadButtons.isEmpty()) {
	            System.out.println("ℹ️ No Upload Report button available. Exiting loop.");
	            shouldLogout = true;   // 👈 mark for logout
	            break;   
	        }

	        WebElement btn = uploadButtons.get(0); // SAFE

	        // ✅ Scroll to center
	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                btn
	        );

	        // ✅ Click Upload Report
	        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
	        Thread.sleep(500);

	        // ---------- YOUR EXISTING LOGIC ----------
	        waitforElement(carbonfield);
	        carbonfield.sendKeys(percentageinputdata);
	        
	        waitforElement(ashcontentfield);
	        ashcontentfield.sendKeys(percentageinputdata);

	        waitforElement(volatilefield);
	        volatilefield.sendKeys(percentageinputdata);

	        waitforElement(moisturefield);
	        moisturefield.sendKeys(percentageinputdata);

	        waitforElement(notetextarea);
	        notetextarea.sendKeys("QA report uploaded with qty");

	        List<String> files = new ArrayList<>();
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");
	        files.add("/home/active34/Downloads/photos /QA club photos/Club 7.png");

	        uploadFilesMultipleTimes(files);

	        scrollBottomofPage();

	        waitforElement(createreportbutton);
	        javascriptclick(createreportbutton);

	        Thread.sleep(1500);

	        System.out.println("✅ One QA report uploaded successfully");

	        // Loop continues → checks again if Upload Report exists
	        
	        if (shouldLogout) {
	            System.out.println("🔐 Logging out vendor after loop completion");

	            waitforElement(vendorprofileicon);
	            javascriptclick(vendorprofileicon);

	            waitforElement(vendorlogoutbutton);
	            javascriptclick(vendorlogoutbutton);
	        }

	    }
	}


}
