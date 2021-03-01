package org.pom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.pom.base.TestBase;

public class HomePage extends TestBase{

    @FindBy(xpath = "//span[@class='user-display']")
    private WebElement userNameLabelElement;

    @FindBy(xpath = "//a[@href='/contacts']")
    private WebElement contactElement;

    @FindBy(xpath = "//a[@href='/deal']")
    private WebElement dealElement;

    @FindBy(xpath = "//a[@href='/tasks']")
    private WebElement tasksElement;

    public HomePage() {
        super();
        PageFactory.initElements(getDriver(), this);
    }

    public String verifyHomePageTitle() {
        return getDriver().getTitle();
    }

    public ContactsPage clickContactLink() {
        contactElement.click();
        return new ContactsPage();
    }

    public DealsPages clickDealLink() {
        dealElement.click();
        return new DealsPages();
    }

}
