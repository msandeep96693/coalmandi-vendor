package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waitHelper 
{
	WebDriver driver;
	
	public waitHelper(WebDriver driver)
	{
		this.driver=driver;
	}

	public void waitElementHelper(WebElement element,Duration time)
	{
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitElementHelper(List<WebElement> elements,Duration time)
	{
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitElementHelper(By element,Duration time)
	{
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
	
	public void waitUntilConditionIsTrue(BooleanSupplier condition, Duration time) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, time);
	    wait.until((WebDriver driver) -> condition.getAsBoolean());
	}
	
	public void waitUntilLoaderToClose(WebElement element,Duration time)
	{
		WebDriverWait wait=new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	public void waituntilelementclickable(WebElement element, Duration time)
	{
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static String captureScreenShot(WebDriver driver , String testName) {
		
		
		File srcScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String Screenshotpath = System.getProperty("user.dir")+"/Screenshots/"+testName+".jpg";
		try {
//		FileUtils.copyFile(srcScreenShot, new File(Screenshotpath));
		org.openqa.selenium.io.FileHandler.copy(srcScreenShot, new File(Screenshotpath));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return Screenshotpath;
	}
}