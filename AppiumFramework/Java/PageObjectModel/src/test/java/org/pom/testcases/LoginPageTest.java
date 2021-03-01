package org.pom.testcases;

import org.pom.base.TestBase;

import org.pom.pages.HomePage;
import org.pom.pages.LoginPage;
import org.pom.util.Helper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

    LoginPage loginPage;

    @Test
    public void loginPageTitleTest() {
        Assert.assertEquals(new LoginPage().getLoginPageTitle(), properties.getProperty("loginPageTitle"));
    }

    @Test
    public void loginPageValidateCRMImageTest() {
        loginPage = new LoginPage();
        boolean flag = loginPage.validateCRMImage();
        Assert.assertTrue(flag);
    }

    @Test(dataProvider = "getData")
    public void loginPageLoginTest(String username, String password) {
        loginPage = new LoginPage();
        HomePage homePage = loginPage.logIn(username, password);
    }

    @Test
    public void loginPageTitleTest1() {
        Assert.assertEquals(new LoginPage().getLoginPageTitle(), properties.getProperty("loginPageTitle"));
    }

    @Test
    public void loginPageValidateCRMImageTest1() {
        loginPage = new LoginPage();
        boolean flag = loginPage.validateCRMImage();
        Assert.assertTrue(flag);
    }

    @Test
    public void loginPageLoginTest1() {
        loginPage = new LoginPage();
        HomePage homePage = loginPage.logIn(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void loginPageTitleTest2() {
        Assert.assertEquals(new LoginPage().getLoginPageTitle(), properties.getProperty("loginPageTitle"));
    }

    @Test
    public void loginPageValidateCRMImageTest2() {
        loginPage = new LoginPage();
        Assert.assertTrue(loginPage.validateCRMImage());
    }

    @Test
    public void loginPageLoginTest2() {
        loginPage = new LoginPage();
        HomePage homePage = loginPage.logIn(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test
    public void loginPageTitleTest3() {
        Assert.assertEquals(new LoginPage().getLoginPageTitle(), properties.getProperty("loginPageTitle"));
    }

    @Test
    public void loginPageValidateCRMImageTest3() {
        loginPage = new LoginPage();
        boolean flag = loginPage.validateCRMImage();
        Assert.assertTrue(flag);
    }

    @Test
    public void loginPageLoginTest3() {
        loginPage = new LoginPage();
        HomePage homePage = loginPage.logIn(properties.getProperty("username"), properties.getProperty("password"));
    }

    @DataProvider
    public Object[][] getData() {
        return Helper.getTestData(properties.getProperty("sheetName"));
    }
}
