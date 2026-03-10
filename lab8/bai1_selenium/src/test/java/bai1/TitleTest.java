package bai1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TitleTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }


    @Test(description = "TC01 - Kiem thu tieu de trang chu")
    public void testTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Tieu de trang khong dung!");
    }


    @Test(description = "TC02 - Kiem thu URL trang chu")
    public void testURL() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("saucedemo"), "URL khong hop le!");
    }


    @Test(description = "TC03 - Kiem thu page source co chua 'Swag Labs'")
    public void testPageSourceContainsSwagLabs() {
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Swag Labs"),
                "Page source khong chua chuoi 'Swag Labs'");
    }


    @Test(description = "TC04 - Kiem thu page source co chua 'user-name'")
    public void testPageSourceContainsUsernameField() {
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("user-name"),
                "Page source khong chua truong username");
    }


    @Test(description = "TC05 - Kiem thu form dang nhap hien thi")
    public void testLoginFormDisplayed() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(username.isDisplayed(), "O username khong hien thi");
        Assert.assertTrue(password.isDisplayed(), "O password khong hien thi");
        Assert.assertTrue(loginButton.isDisplayed(), "Nut Login khong hien thi");
    }


    @Test(description = "TC06 - Kiem thu o username hien thi")
    public void testUsernameFieldDisplayed() {
        WebElement username = driver.findElement(By.id("user-name"));
        Assert.assertTrue(username.isDisplayed(), "O username khong hien thi");
    }

    @Test(description = "TC07 - Kiem thu o password hien thi")
    public void testPasswordFieldDisplayed() {
        WebElement password = driver.findElement(By.id("password"));
        Assert.assertTrue(password.isDisplayed(), "O password khong hien thi");
    }


    @Test(description = "TC08 - Kiem thu nut login hien thi")
    public void testLoginButtonDisplayed() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Nut login khong hien thi");
    }

    @Test(description = "TC09 - Kiem thu o username duoc enable")
    public void testUsernameFieldEnabled() {
        WebElement username = driver.findElement(By.id("user-name"));
        Assert.assertTrue(username.isEnabled(), "O username khong duoc enable");
    }


    @Test(description = "TC10 - Kiem thu o password duoc enable")
    public void testPasswordFieldEnabled() {
        WebElement password = driver.findElement(By.id("password"));
        Assert.assertTrue(password.isEnabled(), "O password khong duoc enable");
    }

    @Test(description = "TC11 - Kiem thu nut login duoc enable")
    public void testLoginButtonEnabled() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isEnabled(), "Nut login khong duoc enable");
    }


    @Test(description = "TC12 - Kiem thu placeholder username")
    public void testUsernamePlaceholder() {
        WebElement username = driver.findElement(By.id("user-name"));
        String placeholder = username.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "Username", "Placeholder username khong dung");
    }


    @Test(description = "TC13 - Kiem thu placeholder password")
    public void testPasswordPlaceholder() {
        WebElement password = driver.findElement(By.id("password"));
        String placeholder = password.getAttribute("placeholder");
        Assert.assertEquals(placeholder, "Password", "Placeholder password khong dung");
    }


    @Test(description = "TC14 - Kiem thu type cua o password")
    public void testPasswordFieldType() {
        WebElement password = driver.findElement(By.id("password"));
        String type = password.getAttribute("type");
        Assert.assertEquals(type, "password", "O password khong co type='password'");
    }


    @Test(description = "TC15 - Kiem thu text cua nut login")
    public void testLoginButtonText() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        String value = loginButton.getAttribute("value");
        Assert.assertEquals(value, "Login", "Text nut login khong dung");
    }


    @Test(description = "TC16 - Kiem thu so luong nut login")
    public void testOnlyOneLoginButtonExists() {
        List<WebElement> loginButtons = driver.findElements(By.id("login-button"));
        Assert.assertEquals(loginButtons.size(), 1, "Khong ton tai dung 1 nut login");
    }

    @Test(description = "TC17 - Kiem thu logo ung dung hien thi")
    public void testAppLogoDisplayed() {
        WebElement appLogo = driver.findElement(By.className("login_logo"));
        Assert.assertTrue(appLogo.isDisplayed(), "Logo ung dung khong hien thi");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}