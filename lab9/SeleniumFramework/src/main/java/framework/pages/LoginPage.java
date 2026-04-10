package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage login(String user, String pass) {

        waitAndType(username, user);
        waitAndType(password, pass);

        waitAndClick(loginButton);

        return new InventoryPage(driver);
    }

    public LoginPage loginExpectingFailure(String user, String pass) {

        waitAndType(username, user);
        waitAndType(password, pass);

        waitAndClick(loginButton);

        return this;
    }

    public boolean isErrorDisplayed() {

        return errorMessage.isDisplayed();
    }
}