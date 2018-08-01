package Pack2;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

	public class Testsuite extends Suitecmds {
    		@Test(priority=1)
	public static void print() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String FilePath="D:\\2018\\iQITA\\Files\\Suite.xlsx";
		readSuiteSheet(FilePath,"Sheet1");
		//Reporter.log("D:\\Logs\\TestLog.txt",true);
		}

	}
	

