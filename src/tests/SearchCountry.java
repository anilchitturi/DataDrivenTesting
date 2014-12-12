package tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class SearchCountry {

private WebDriver driver;
private String baseUrl;
private static String OS = System.getProperty("os.name").toLowerCase();


@BeforeSuite
public void setUp() throws Exception {
	
		System.setProperty("webdriver.chrome.driver","src/resources/chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "http://www.wikipedia.org/";
	}

@Test
public void testSearchCountry() throws Exception {
	driver.get(baseUrl + "/wiki/Main_Page");
	ReadXLS readXls = new ReadXLS();
    List dataList = readXls.getData();
    for (int i = 1; i < dataList.size(); i++) {
        String[] testCase = new String[5];
        String[] test = (String[]) dataList.get(i);
        String countryName = test[0];
        String countryDesc = test[1];
        driver.findElement(By.id("searchInput")).clear();
        driver.findElement(By.id("searchInput")).sendKeys(countryName);
        driver.findElement(By.id("searchButton")).click();
        String str = driver.findElement(
        By.xpath("//h1[@id='firstHeading']/span")).getText();
        System.out.println(countryDesc);
        Assert.assertTrue(str.contains(countryName));
    }
}

@AfterSuite
public void tearDown() throws Exception {
driver.quit();
}
}