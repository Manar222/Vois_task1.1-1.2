package tests;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestBase {

    public static WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void startDriver(@Optional("chrome") String browserName) throws InterruptedException {

        if (browserName.equalsIgnoreCase("chrome")) {

             driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
         Dimension windowSize = new Dimension(1024, 768);

        driver.manage().window().setSize(windowSize);

        driver.get("http://amazon.eg/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        WebDriver driver = null;
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("test failed: " + result.getName());
            System.out.println("taking screenshot");
           
            
            Helper.takeScreenShot(driver, result.getName());
        }
        driver.close();
    } 








}