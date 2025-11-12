package Basepackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import vendorpageobject.vendorBusinesscreationpage;
import vendorpageobject.vendorsigninpage;




public class vendorBaseclass {
	
	public Properties prop;
	public WebDriver driver;
	public vendorsigninpage vendorsign;
	public vendorBusinesscreationpage vendorcreate;
	
	
	
	public vendorBaseclass() { 
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "//src//test//resources//config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@BeforeMethod
	public  void setup()
	{
		String Browsername = prop.getProperty("browser");
		
		if(Browsername.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		} 
		else if (Browsername.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(Browsername.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Given Browsername is not matching :- "+Browsername);
		}
		
		driver.manage().window().maximize();
//		driver.get("https://v3opend.tech-active.com");
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	
	@AfterMethod
	public void tear(ITestResult result)
	{
		if (ITestResult.FAILURE == result.getStatus()) {
	        // Take screenshot if scenario failed
			TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			
         // Save screenshot to folder
            String screenshotName = "screenshots/" + result.getName() + ".png";
            try {
                Files.createDirectories(Paths.get("screenshots"));
                Files.write(Paths.get(screenshotName), screenshot);
                System.out.println("Screenshot saved: " + screenshotName);
            } catch (IOException e) {
                e.printStackTrace();
            }   
	    }
		
		if(driver != null)
		{
			driver.quit();
			driver = null;
		}
	}
	
	
	public String setRandomCampaignName()
	   {
		 String randomstring=RandomStringUtils.random(4,"abcdefghijklmnopqrstuvwxyz");
		 return "Test campaign"+randomstring;
	   }
	   
	   public String setRandomBusinessName()
	   {
		 String randomstring=RandomStringUtils.random(4,"abcdefghijklmnopqrstuvwxyz");
		 return "Remember me"+randomstring;
	   }
	   
	   public String setRandomFirstName()
	   {
		 String randomstring=RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		 return "Sandeep"+randomstring;
	   }
	   
	   public String setRandomLastName()
	   {
		 String randomstring=RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		 return "Rathod"+randomstring;
	   }
	   
	   public String setRandomEmail()
	   {
		 String randomstring=RandomStringUtils.random(3,"abcdefghijklmnopqrstuvwxyz");
		 return "sandeep"+"+"+randomstring+"@rokkun.io";
	   }
	   public String setRandomMobileNumber()
	   {
		  String randomnumeric=RandomStringUtils.randomNumeric(6);
		  return "9620"+randomnumeric;
	   }
	   
//	   public String setRandomalphanumber()
//	   {
//		  String randomalphanumeric=RandomString.randomAlphanumeric(5);
//		  return randomalphanumeric;
//	   }
	  
	   public void robotClickAction() throws AWTException
	   {
			Robot rob=new Robot();
			rob.keyPress(KeyEvent.VK_ENTER);
			rob.keyRelease(KeyEvent.VK_ENTER);
	   }
	
	
	
}