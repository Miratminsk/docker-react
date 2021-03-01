package org.pom.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pom.extentreports.ExtentReportsNG;
import org.pom.util.Helper;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static Properties properties;
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public TestBase() {
        setUpProperties();
    }

    public void driverInitialization() {
        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/java/org/pom/util/drivers/chromedriver");
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/main/java/org/pom/util/drivers/geckodriver");
                driver.set(new FirefoxDriver());
                break;
        }
        System.out.println(Thread.currentThread().getId());
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().pageLoadTimeout(Helper.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(Helper.IMPLICIT_WAIT, TimeUnit.SECONDS);
        getDriver().get(properties.getProperty("URL"));
    }

    public void setUpProperties() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/org/pom/config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {
        driverInitialization();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        if(result.getStatus()==result.FAILURE || result.getStatus()==result.SKIP) {
            String screenshotPath = Helper.getScreenshot(getDriver(), result.getName());
            System.out.println(screenshotPath);
            result.setAttribute("screenshotPath", screenshotPath); //sets the value the variable/attribute screenshotPath as the path of the sceenshot
        }
        getDriver().quit();
        driver.remove();
    }
}
