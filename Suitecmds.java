package Pack2;
	import java.io.File;
	import org.apache.log4j.Logger;
    import org.apache.log4j.PropertyConfigurator;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

	import org.apache.commons.io.FileUtils;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebDriverException;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.Reporter;
    import org.testng.annotations.Test;

	public class Suitecmds extends Action{
		
		private static XSSFSheet sheet;
		private static XSSFSheet sheename;
		private static XSSFWorkbook wb;
		private static XSSFCell cell;
		private static XSSFRow row;
		FileInputStream fs;
		static String Object_xpath;
		static String objectname;
		static String inputvalue;
		static String script_command;
		static String script_object;
		static String script_input;
		static WebDriver driverobj;
		static String scriptsheetname;
	    static	XSSFSheet scriptsheet;
		static LinkedHashMap<String,String> hm=new LinkedHashMap<String,String>();
		
		
		static List<String> sheetNames = new ArrayList<String>();
		String FilePath="D:\\2018\\iQITA\\Files\\Suite.xlsx";
		String suitesheet="Sheet1";
		
		 static Logger logger =  Logger.getLogger("Suitecmds");
	     //PropertyConfigurator.configure("Log4j.properties");
	     
	     
		/*public static void screenshots() throws IOException
		{
					
			try {
				   String fileWithPath="D:\\snapshots\\";
				   TakesScreenshot scrshot = (TakesScreenshot) driverobj; 
				   File SrcFile=scrshot.getScreenshotAs(OutputType.FILE);
				   String snapname =  new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'.png'").format(new Date());
				   File DestFile=new File(fileWithPath + snapname);
				   FileUtils.copyFile(SrcFile, DestFile);
				   System.out.println("shot taken");
				  } catch (WebDriverException e) {
				   System.out.println("search error"+e.getMessage());
				   e.printStackTrace();
				  }
			}*/
		
		public static void readSuiteSheet(String fileName,String sheetName) throws IOException, InterruptedException
		 {
			PropertyConfigurator.configure("Log4j.properties");
			System.out.println("Inside Function");
			System.out.println("FileName" + fileName);
			System.out.println("SheetName" + sheetName);
			
			FileInputStream SuiteFile = new FileInputStream(fileName);
			wb = new XSSFWorkbook(SuiteFile);
			sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			System.out.println("RowCount" + rowCount);
			
			for(int i=1;i<=rowCount; i++)
			{
				Row row=sheet.getRow(i);
				objectname=sheet.getRow(i).getCell(0).getStringCellValue();
				Object_xpath=sheet.getRow(i).getCell(1).getStringCellValue();
				hm.put(objectname,Object_xpath);
				readScript(objectname,Object_xpath);
			}
		 }
		
		public static void readScript(String key, String value) throws IOException
		{
			
			System.out.println("KEY" + key);
			System.out.println("VALUE" + value);
			
			String ScriptFilePath="D:\\2018\\iQITA\\Files\\Script.xlsx";
			FileInputStream ScriptFile = new FileInputStream(ScriptFilePath);
			
			
			wb = new XSSFWorkbook(ScriptFile);
			
		  for (int i=0; i<wb.getNumberOfSheets(); i++) {
			   // sheetNames.add( wb.getSheetName(i) );
			   	if(key.equals("Y") && value.equals(wb.getSheetName(i)))
				{
			   		
			   		System.out.println("Inside Equals");
			   		//XSSFSheet scriptsheetname=wb.getSheetAt(i);
			   		String scriptsheetname= wb.getSheetName(i);
			   		System.out.println("EQUALS");
			   		compare(scriptsheetname);
			   		
			   	   		
			   	}
			}
			
		
		}
	public static void compare(String scriptsheetname) throws IOException
	{
				
				//System.out.println("SCRIPTSHEET" + scriptsheetname);
	System.out.println("######Compare Called########");	
	String ScriptFilePath="D:\\2018\\iQITA\\Files\\Script.xlsx";
	FileInputStream ScriptFile = new FileInputStream(ScriptFilePath);
	wb = new XSSFWorkbook(ScriptFile);
	scriptsheet = wb.getSheet(scriptsheetname);
	XSSFRow row=scriptsheet.getRow(1);
	String cell=row.getCell(1).toString();
	System.out.println("CELL" + cell);
	System.out.println("COMAPARE**********" + scriptsheetname);
	readObjectSheet(cell,scriptsheetname);
	}
			
			
	public static void readObjectSheet(String cell, String value) throws IOException
	{
		System.out.println("CELL" + cell);
		System.out.println("VALUE" + value);
		scriptsheetname=value;
		System.out.println("Inside Read Object Sheeet" + scriptsheetname);
		String ObjectFilePath="D:\\2018\\iQITA\\Files\\Object.xlsx";
				
		FileInputStream ObjectFile = new FileInputStream(ObjectFilePath);
		wb = new XSSFWorkbook(ObjectFile);
				
		for (int i=0; i<wb.getNumberOfSheets(); i++) {
				    
		XSSFSheet sheet=wb.getSheetAt(i);
	   	System.out.println(wb.getSheetName(i));
		if(cell.equals( wb.getSheetName(i)))
		{
			System.out.println("EQUALS OBJECT");
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			System.out.println("RowCount" + rowCount);
						
			for(int j=1;j<rowCount+1;j++)
			{
				Row row=sheet.getRow(j);
				objectname=sheet.getRow(j).getCell(0).getStringCellValue();
				Object_xpath=sheet.getRow(j).getCell(1).getStringCellValue();
				hm.put(objectname,Object_xpath);
			}
			for(Map.Entry m:hm.entrySet()){  
				   System.out.println(m.getKey()+" "+m.getValue());  
				 }
						 
					
						
			String Filename="D:\\2018\\iQITA\\Files\\Script.xlsx";
			FileInputStream ScriptFile = new FileInputStream(Filename);
			wb = new XSSFWorkbook(ScriptFile);
			sheet = wb.getSheet(scriptsheetname);
						
			int rowCount1 = sheet.getLastRowNum()-sheet.getFirstRowNum();
							
			System.out.println("FINAL SCRIPT SHEEET&&&&&&" + scriptsheetname);
						
		    System.out.println("Contents of Script Sheet" + sheet.getRow(2).getCell(1));
					        									
						System.out.println("****RowCOUNT" + rowCount1);
						for(i=2;i<rowCount1+1; i++)
						{
							Row row=sheet.getRow(i);
							script_command=sheet.getRow(i).getCell(0).getStringCellValue();
							script_object=sheet.getRow(i).getCell(1).getStringCellValue();
							
							XSSFCell cellempty = sheet.getRow(i).getCell(2);
							if (cellempty != null) {
							script_input=sheet.getRow(i).getCell(2).getStringCellValue();
							}
							
							System.out.println("SCRIPT_COMMAND" + script_command);
							System.out.println("script_object" + script_object);
							System.out.println("script_input" + script_input);
							
							for(Map.Entry m:hm.entrySet()){
								if(m.getKey().equals(script_object))
								{
									System.out.println("script_object" + script_object);
									System.out.println("Object Sheet" + m.getKey());
									System.out.println("EQUALS");
									String script_xpath1=(String) m.getValue();
									System.out.println("SCRIPT_XPATH1" + script_xpath1);
									System.out.println("SCRIPT_INPUT" + script_input);
									
									show(script_command,script_object,script_input,script_xpath1,scriptsheetname);
								}
							//}
							
						// }
						
							/*else {
								for(Map.Entry m:hm.entrySet()){
									if(m.getKey().equals(script_object))
									{
										System.out.println("script_object" + script_object);
										System.out.println("Object Sheet" + m.getKey());
										System.out.println("EQUALS");
										String script_xpath1=(String) m.getValue();
										System.out.println("SCRIPT_XPATH1" + script_xpath1);
										//System.out.println("SCRIPT_INPUT" + script_input);
										
										show(script_command,script_object,script_xpath1,scriptsheetname);
									}
								}*/
							}
								
							
							}
							
						}
						
						
					}
				}
				   	
				
		

			

	private static void show(String script_command2, String script_object2, String script_input2, String script_xpath, String scriptsheetname) throws IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("SCRIPT_COMMAND2" + script_command2);
		System.out.println("SCRIPT_OBJECT2" + script_object2);
		//System.out.println("SCRIPT_INPUT2" + script_input2);
		System.out.println("SCRIPT_XPATH" + script_xpath);
		//Reporter.log("D:\\Logs\\TestLog.txt");
		if(script_command2.equals("open"))
		{
			Action.open(script_xpath);
			logger.info("Invoking browser");
			
		}
		
		else if(script_command2.equals("type"))
		{
			type(script_xpath,script_input2);
			logger.info("Sending values" + script_input2);
		}
		
		else if(script_command2.equals("click"))
			{
				click(script_xpath);
				logger.info(script_command2+ "Element clicked");
			}
		
	}


	private static void show(String script_command2, String script_object2, String script_xpath, String scriptsheetname) throws IOException {	
		
		 if(script_command2.equals("click"))
		{
			click(script_xpath);
			logger.info(script_command2+ "Element clicked");
		}
		}
	}
	
		
	/*private static void open(String script_xpath)
	{
		
		System.out.println("Final" + script_xpath);
	    String projectpath=System.getProperty("user.dir");
		String driverpath=projectpath+"//Driver//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
		System.out.println("Driverpath" + driverpath);
		driverobj=new ChromeDriver();
		driverobj.get(script_xpath); 
	}

	private static void type(String script_xpath, String script_input) throws IOException
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
		





	private static void click(String script_xpath)
	{
		
	String xpath1[]=script_xpath.split("=");
		
		System.out.println("After Splitting xpath1[0]" + xpath1[0]);
		System.out.println("After Splitting xpath[1]" + xpath1[1]);
		
		
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
		
	
	}*/

	
	

		



