
package vendorpageobject;

import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.waitHelper;


public class vendorBasicpage 
{
	waitHelper wait;
	WebDriver driver;


	public vendorBasicpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitforElement(WebElement element)
	{
		wait=new waitHelper(driver);
		wait.waitElementHelper(element, Duration.ofSeconds(30));
	}
	
	public void waitElementHelper(List<WebElement> elements)
	{
		wait.waitElementHelper(elements, Duration.ofSeconds(30));
	}
	
	public void waitElementHelper(By element)
	{
		wait.waitElementHelper(element, Duration.ofSeconds(30));
	}
	
	public void waitUntilConditionIsTrue(BooleanSupplier condition)
	{
		wait.waitUntilConditionIsTrue(condition, Duration.ofSeconds(30));
	}
	
	public void waitForLoaderToClose(WebElement element)
	{
		wait=new waitHelper(driver);
		wait.waitUntilLoaderToClose(element, Duration.ofSeconds(10));
	}
	
	public void waituntilelementvisibleandclickable(WebElement element)
	{
		wait = new waitHelper(driver);
		wait.waituntilelementclickable(element, Duration.ofSeconds(30));
	}
		
	public void scrollUntilElementVisible(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		//element.click();
	}
	
	public void Hiddenelement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.display='block';",element);
	}
	
	public void scrollBottomofPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");	
	}
	
	public void scrollDownNumberofpixels()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	public void Hiddennavigationmessage(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.display='block';",element);
	}
	
	public void javascriptclick(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element );
	}
	
	public void selectDropdownOption(List<WebElement> dropdownOptions, String optionName) {
	    boolean found = false;

	    for (WebElement option : dropdownOptions) {
	    	System.out.println("option name :- "+ option.getText());
	    	
	        if (option.getText().equalsIgnoreCase(optionName) || option.getText().contains(optionName)) {
	            javascriptclick(option);
	            found = true;
	            break;
	        }
	    }

	    if (!found) {
	        System.out.println("Option not found: " + optionName);
	    }
	}
	
	// --------------------------------------------------------------------------------
	
	
	 public static WebElement waitForClick(WebDriver driver, WebElement element, int timeout) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	       // wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(element)));
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	
	
	public static void safeClick(WebDriver driver, WebElement element, int timeout) {
        try {
            waitForClick(driver, element, timeout).click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
	
	public String setRandomBusinessName()
	   {
		 String randomstring=RandomStringUtils.random(4,"abcdefghijklmnopqrstuvwxyz");
		 return "Remember me"+randomstring;
	   }
	
	 public String setRandomName()
	   {
		 String randomstring=RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		 return "Sandeep"+randomstring;
	   }
	 
	 public String setRandomEmail()
	   {
		 String randomstring=RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		 return "sandeep"+"+QA"+randomstring+"@rokkun.io";
	   }
	 
	 public String setRandomMobileNumber()
	   {
		  String randomnumeric=RandomStringUtils.randomNumeric(6);
		  return "9620"+randomnumeric;
	   }
	
}
