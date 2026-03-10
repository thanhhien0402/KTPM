package bai1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Hàm dùng chung để nhập username, password và click login
    public void doLogin(String username, String password) {
        WebElement txtUsername = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement txtPassword = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement btnLogin = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("login-button")));

        txtUsername.clear();
        txtUsername.sendKeys(username);

        txtPassword.clear();
        txtPassword.sendKeys(password);

        btnLogin.click();
    }

    @Test(description = "TC01 - Dang nhap thanh cong voi tai khoan hop le")
    public void testLoginSuccess() {
        doLogin("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.contains("inventory.html"),
                "Dang nhap thanh cong nhung khong chuyen den trang inventory.html");
    }

    @Test(description = "TC02 - Dang nhap sai mat khau")
    public void testLoginWrongPassword() {
        doLogin("standard_user", "sai_mat_khau");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        String actualError = errorMessage.getText();

        Assert.assertTrue(actualError.toLowerCase().contains("username and password do not match"),
                "Khong hien thi thong bao loi khi nhap sai mat khau");
    }

    @Test(description = "TC03 - Bo trong username")
    public void testLoginEmptyUsername() {
        doLogin("", "secret_sauce");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        String actualError = errorMessage.getText();

        Assert.assertTrue(actualError.contains("Username is required"),
                "Thong bao loi khong dung khi bo trong username");
    }

    @Test(description = "TC04 - Bo trong password")
    public void testLoginEmptyPassword() {
        doLogin("standard_user", "");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        String actualError = errorMessage.getText();

        Assert.assertTrue(actualError.contains("Password is required"),
                "Thong bao loi khong dung khi bo trong password");
    }

    @Test(description = "TC05 - Dang nhap voi tai khoan bi khoa")
    public void testLoginLockedUser() {
        doLogin("locked_out_user", "secret_sauce");

        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        String actualError = errorMessage.getText();

        Assert.assertTrue(actualError.contains("Sorry, this user has been locked out"),
                "Thong bao loi khong dung cho tai khoan bi khoa");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}