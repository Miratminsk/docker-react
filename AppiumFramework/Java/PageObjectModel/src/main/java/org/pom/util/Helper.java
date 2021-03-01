package org.pom.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pom.base.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Helper extends TestBase {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 5;
    public static long EXPLICIT_WAIT = 5;
    private static Workbook workbook;

    WebDriverWait wait;


    public void switchToFrame() {
        getDriver().switchTo().frame("mainpanel");
    }

    public void waitElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(getDriver(), EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitInvisibilityOf(WebElement element) {
        wait = new WebDriverWait(getDriver(), EXPLICIT_WAIT);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void click(WebElement element) {
        waitElementToBeClickable(element);
        try {
            element.click();
        } catch (ElementNotVisibleException enve) {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView();", element);
            js.executeScript("arguments[0].click();", element);
        }
    }

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(properties.getProperty("testDataSheetPath"));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        try {
            assert file != null;
            workbook = WorkbookFactory.create(file);
        } catch (IOException ife) {
            ife.printStackTrace();
        }
        Sheet sheet = workbook.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum() - 1][sheet.getRow(0).getLastCellNum()];
        for (int i = 0; i < sheet.getLastRowNum() - 1; i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
            }
        }
        return data;
    }

    public static String getScreenshot(WebDriver driver, String screenShotName) throws IOException {
        //String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "screenshots/" + System.currentTimeMillis() + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination.getAbsoluteFile());
        return destination;
    }

//    public static String getScreenshot() throws IOException {
//        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png"));
//        return null;
//    }
}
