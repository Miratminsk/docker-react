package org.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pom.base.TestBase;
import org.pom.util.Helper;

public class LoginPage extends TestBase {

    Helper helper;

    @FindBy(xpath = "//a[text()='Log In']")
    @CacheLookup//store element in cash. If refresh page StaleElement
    private WebElement logInLink;

    @FindBy(name = "email")
    @CacheLookup
    private WebElement emailField;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(xpath = "//div[@class='ui fluid large blue submit button']")
    @CacheLookup
    private WebElement logInButton;

    @FindBy(xpath = "//a[text()='Login']")
    @CacheLookup
    private WebElement SignInLink;

    @FindBy(xpath = "//a[@class='navbar-brand']//img[@class='img-responsive']")
    @CacheLookup
    private WebElement titleImage;

    @FindBy(xpath = "//div[@id='preloader']")
    @CacheLookup
    private WebElement preloader;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
        helper = new Helper();
    }

    public String getLoginPageTitle() {
        return getDriver().getTitle();
    }

    public boolean validateCRMImage() {
        return titleImage.isDisplayed();
    }

    public HomePage logIn(String email, String password) {
        helper.waitInvisibilityOf(preloader);
        helper.click(logInLink);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        helper.waitElementToBeClickable(logInButton);
        helper.click(logInButton);
        return new HomePage();
    }
}
