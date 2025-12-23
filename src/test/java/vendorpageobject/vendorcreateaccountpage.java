package vendorpageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class vendorcreateaccountpage extends vendorBasicpage {
	
	public vendorcreateaccountpage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "(//button[@type='button'])[2]")
	private WebElement createanaccountbutton;
	
//	@FindBy(xpath = "((//h2[.='Choose User Role'])[2]/../following-sibling::div)[2]/button/span")
	@FindBy(xpath = "//span[.='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath = "(//input[@type='text'])[1]") 
	private WebElement fullnametextfield;

	@FindBy(xpath = "(//input[@type='text'])[2]")
	private WebElement whatsappnumbertextfield;
	
	@FindBy(xpath = "(((//label[@title='WhatsApp Number (Primary)'])[2]/../following-sibling::div/div)[1]/div/span/span/span/div/div/span/span)[1]/input") 
	private WebElement countrycodetextfiled;
	
//	@FindBy(xpath = "(//label[@title='Email ID'])[2]/../following-sibling::div/div/div/input") 
	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailIDtextfield;
	
	@FindBy(xpath = "//button[@type='submit']/span")
	private WebElement sendotpbutton;

	@FindBy(xpath = "//*[@id='root']/div[2]/div/div/form/div[1]/div[2]/div/div/div/div/div/div/input")
	private List<WebElement> whatsappotpinputfield;
	
	@FindBy(xpath = "//*[@id='root']/div[2]/div/div/form/div[2]/div[2]/div/div/div/div/div/div/input")
	private List<WebElement> emailotpinputfield;
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement enterpassword;
	
	@FindBy(xpath = "(//input[@type='password'])[2]")
	private WebElement enterconfirmpassword;
	
	@FindBy(xpath = "//span[.='Create Account']")
	private WebElement createaccountbtn;
	
	@FindBy(xpath = "//span[.='Logout']/..") // (//button[@type='button'])[1]
	private WebElement logoutbutton;
	
	@FindBy(xpath = "//h3[.='Account Created']")
	private WebElement Accountcreatedtext;
	
	@FindBy(xpath = "//h2[.='Create Password']")
	private WebElement createpasswordtext;
	
	@FindBy(xpath = "//div[.='Account created successfully']")
	private WebElement accountcreateconfirmationmessage;
	
	
	
	public void createbusinessaccount(String mobilenumber, String createpassword, String confirmpassword) throws InterruptedException
	{
//		vendorsigninpage signin =  new vendorsigninpage(driver);
//		signin.vendorsignin(email, pwd);
		
		// click on new to coalmandi button
		waitforElement(createanaccountbutton);
		createanaccountbutton.click();
		
		// enter a fullname into field
		try {
			waitforElement(fullnametextfield);
			fullnametextfield.sendKeys(setRandomName());
		} catch (StaleElementReferenceException e) {
			waitforElement(fullnametextfield);
			fullnametextfield.sendKeys(setRandomName());
		}
		
		
		
		// enter a whatsapp number 
		waitforElement(whatsappnumbertextfield);
		whatsappnumbertextfield.sendKeys(mobilenumber);
		
		// enter a emailID
		waitforElement(emailIDtextfield);
		emailIDtextfield.sendKeys(setRandomEmail());
		
		// click on send otp button
		Thread.sleep(2000);
		javascriptclick(sendotpbutton);
		
//		// enter otp into textfields and click enter button on keyboard
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
//		wait.until(d -> whatsappotpinputfield.stream().allMatch(field -> !field.getAttribute("value").isEmpty()));
		
		Thread.sleep(25000);
		
		// enter password
		waitforElement(enterpassword);
		enterpassword.sendKeys(createpassword);

		// enter confirm password
		waitforElement(enterconfirmpassword);
		enterconfirmpassword.sendKeys(confirmpassword);
		
		// click on create account button
		waitforElement(createaccountbtn);
		javascriptclick(createaccountbtn);
		
		
////		// click on logout button
//		Thread.sleep(3000);
//		waitforElement(logoutbutton);
//		javascriptclick(logoutbutton);
////		
////		// check navigate to the loginpage
//		System.out.println(driver.getCurrentUrl()); 
	}


}
