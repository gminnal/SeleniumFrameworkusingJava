package Pack2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Action {

	static WebDriver driverobj;
	public static void open(String script_xpath)
	{
		
		System.out.println("Final" + script_xpath);
	    String projectpath=System.getProperty("user.dir");
		String driverpath=projectpath+"//Driver//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
		System.out.println("Driverpath" + driverpath);
		driverobj=new ChromeDriver();
		driverobj.get(script_xpath); 
	}

	public static void type(String script_xpath, String script_input) throws IOException
	{
		
		System.out.println("XPATH OF FIRSTNAME" + script_xpath);
		System.out.println("XPATH OF INPUT" + script_input);
		
		
		String xpath1[]=script_xpath.split("=");
		
		System.out.println("After Splitting xpath1[0]" + xpath1[0]);
		System.out.println("After Splitting xpath[1]" + xpath1[1]);
		
		try {
		if(xpath1[0].equals("name"))
		{
		 WebElement txtbox_username = driverobj.findElement(By.name(xpath1[1]));
		 txtbox_username.sendKeys(script_input);
	     }
		
		else if(xpath1[0].equals("id"))
		{
		 WebElement txtbox_username = driverobj.findElement(By.id(xpath1[1]));
		 txtbox_username.sendKeys(script_input);
	     }
		
		else if(xpath1[0].equals("xpath"))
		{
			if(xpath1[2].equals(""))
			{
				System.out.println("Blank xpath1[2]");
			}
			
			else {
			System.out.println("SCRIPT INPUT" + script_input);
			System.out.println("After Splitting xpath1[2]" + xpath1[2]);
			WebElement txtbox_username=driverobj.findElement(By.xpath(xpath1[1]+"="+xpath1[2]));	
		    txtbox_username.sendKeys(script_input);
			
			}
		}
		}catch(Exception e)
		{
			screenshots();
			System.out.println(e.getMessage());
		}
		
		
		
		}
		





	public static void click(String script_xpath) throws IOException
	{
		
	 String xpath1[]=script_xpath.split("=");
		
		System.out.println("After Splitting xpath1[0]" + xpath1[0]);
		System.out.println("After Splitting xpath[1]" + xpath1[1]);
		
		try {
		if(xpath1[0].equals("name"))
		{
		 WebElement txtbox_username = driverobj.findElement(By.name(xpath1[1]));
		 txtbox_username.click();
	     }
		
		else if(xpath1[0].equals("id"))
		{	
		 WebElement txtbox_username = driverobj.findElement(By.id(xpath1[1]));
		 txtbox_username.click();
		
	     }
		
		else if(xpath1[0].equals("xpath"))
		{
			System.out.println("After Splitting xpath1[2]" + xpath1[2]);
			WebElement txtbox_username=driverobj.findElement(By.xpath(xpath1[1]+"="+xpath1[2]));	
		    txtbox_username.click();
		}
		}catch(Exception e)
		{
			screenshots();
		}
	
	}
	public static void screenshots() throws IOException
	{
				
		try {
			   String fileWithPath="D:\\snapshots\\";
			   TakesScreenshot scrshot = (TakesScreenshot) driverobj; 
			   File SrcFile=scrshot.getScreenshotAs(OutputType.FILE);
			   String snapname =  "Snapshot"+ new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'.png'").format(new Date());
			   File DestFile=new File(fileWithPath + snapname);
			   FileUtils.copyFile(SrcFile, DestFile);
			   System.out.println("shot taken");
			  } catch (WebDriverException e) {
			   System.out.println("search error"+e.getMessage());
			   e.printStackTrace();
			  }
		}
	
}
