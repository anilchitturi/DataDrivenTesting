package tests;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class google {
	private WebDriver driver;

	@BeforeSuite
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver","src/resources/chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().window().maximize();
		}
	
	 @Test
	 public void test() throws Exception {
	  driver.get("http://www.google.co.in/");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.id("gbqfq")).clear();
	  driver.findElement(By.id("gbqfq")).sendKeys("Testing");
	  driver.findElement(By.id("gbqfq")).sendKeys(Keys.ENTER);
	  driver.findElement(By.linkText("Software testing - Wikipedia, the free encyclopedia")).click();
	  String s = driver.getTitle();
	  writereport(s);
	  
	 }

	  @AfterClass(alwaysRun = true)
	    public void tearDown() throws Exception {
	        if (driver != null)
	        {
	            driver.quit();
	        }
	    }



	public void writereport(String text) 
	       { 
	        try
	        {
	       FileOutputStream f = new FileOutputStream("E:\\output.xls",true);
	       WritableWorkbook book = Workbook.createWorkbook(f); 
	       WritableSheet sheet = book.createSheet("output", 0);
	       Label l = new Label(0, 0, text);
	       sheet.addCell(l);
	       book.write(); 
	       book.close(); 
	        }
	        catch (Exception e)
	        {
	         e.printStackTrace();
	        }
	        }
}