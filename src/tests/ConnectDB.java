package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.thoughtworks.selenium.SeleneseTestBase;

public class ConnectDB extends SeleneseTestBase{
     WebDriver driver;
     String url ="";
     @BeforeTest
public void setUp() throws Exception{
     driver = new FirefoxDriver();
     url = "file:///E:/datatest.html";
     driver.get(url);
}
     @Test
     public void CreateDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, InterruptedException{
           //Prepare connection
           String url1 ="jdbc:mysql://localhost:3306/demo";
           // Load Microsoft SQL Server JDBC driver
           String dbClass = "com.mysql.jdbc.Driver";
           Class.forName(dbClass).newInstance();
           //Get connection to DB
           Connection con = DriverManager.getConnection(url1, "flixtrix", "local");
           //Create Statement
           Statement stmt = (Statement) con.createStatement();
           // method which returns the requested information as rows of data
           ResultSet result = (ResultSet) stmt.executeQuery("select * from employee");
           if(result.next())
           {
                String id = result.getString("ID");
                String info = result.getString("Info");
                driver.getCurrentUrl();
                WebElement a = driver.findElement(By.id("txtID"));
                a.sendKeys(id);
                Thread.sleep(1000);
                WebElement b = driver.findElement(By.id("txtInfo"));
                b.sendKeys(info);
                Thread.sleep(1000);
                WebElement btnclick = driver.findElement(By.id("btnclick"));
                btnclick.click();
                System.out.print("Passed");
           }
     }
    
     @AfterTest
public void tearDown(){
    	 driver.quit();
}
}