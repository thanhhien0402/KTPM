package dtm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    // username field
    @FindBy(id = "user-name")
    private WebElement userNameField;

    // password field
    @FindBy(id = "password")
    private WebElement passwordField;

    // login button
    @FindBy(id = "login-button")
    private WebElement loginButton;

    // error message
    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }


    public void nhapUsername(String username) {

        userNameField.clear();

        if (username != null) {
            userNameField.sendKeys(username);
        }
    }


    public void nhapPassword(String password) {

        passwordField.clear();

        if (password != null) {
            passwordField.sendKeys(password);
        }
    }


    public void clickDangNhap() {

        loginButton.click();
    }


    public void dangNhap(String user, String pass) {

        nhapUsername(user);

        nhapPassword(pass);

        clickDangNhap();
    }


    public String layThongBaoLoi() {

        try {

            if (errorMessage.isDisplayed()) {
                return errorMessage.getText();
            }

        } catch (Exception e) {
            return null;
        }

        return null;
    }


    public boolean isDangOTrangSanPham() {

        return driver.getCurrentUrl().contains("inventory.html");
    }
}