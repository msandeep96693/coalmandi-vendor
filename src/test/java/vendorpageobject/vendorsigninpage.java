package vendorpageobject;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class vendorsigninpage extends vendorBasicpage {

	public vendorsigninpage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath = "(//input[@type='text'])[1]")
	private WebElement emailFieldLocator;
	
	@FindBy(xpath = "(//input[@type='password'])[1]")
	private WebElement passwordFieldLocator;
	
	@FindBy(xpath="(//button[@type='submit'])[1]") 
	private WebElement btnLogin;
	
	@FindBy(xpath = "//img[@alt='Coal Mandi']/../following-sibling::nav//button")
	private List<WebElement> btnsSideBar;
	
	@FindBy(xpath = "//span[@aria-label='user']/..")
	private WebElement clickonprofileicon;
	
	@FindBy(xpath = "//button[.='Logout']")
	private WebElement logoutbtn;
	
	public void vendorsignin(String email, String pwd) throws InterruptedException
	{
		// enter a emailID
		waitforElement(emailFieldLocator);
		emailFieldLocator.sendKeys(email);
		// enter a password
		waitforElement(passwordFieldLocator);
		System.out.println("password :- "+ passwordFieldLocator);
		System.out.println("password :- "+ pwd);
		passwordFieldLocator.sendKeys(pwd);
		// click on signin button
		waitforElement(btnLogin);
		javascriptclick(btnLogin);
		
//		Thread.sleep(2000);
//		waitforElement(clickonprofileicon);
//		javascriptclick(clickonprofileicon);
//		
//		waitforElement(logoutbtn);
//		javascriptclick(logoutbtn);
//		
//		Thread.sleep(2000);
	}
}
