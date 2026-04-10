package framework.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {

        if (browser == null) {
            browser = "chrome";
        }

        return switch (browser.toLowerCase()) {

            case "firefox" -> createFirefoxDriver();

            default -> createChromeDriver();
        };
    }

    private static WebDriver createChromeDriver() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {

        WebDriverManager.firefoxdriver().setup();

        return new FirefoxDriver();
    }
}