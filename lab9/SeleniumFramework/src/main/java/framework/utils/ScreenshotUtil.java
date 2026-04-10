package framework.utils;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String testName) {

        try {

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dest = new File("target/screenshots/" + testName + ".png");

            dest.getParentFile().mkdirs();

            Files.copy(src.toPath(), dest.toPath());

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}