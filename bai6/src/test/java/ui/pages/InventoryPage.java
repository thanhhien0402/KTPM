package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver;

    private final By pageTitle = By.className("title");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("inventory")
                && driver.getTitle().equals("Swag Labs")
                && driver.findElement(pageTitle).isDisplayed();
    }

    public void addTwoProducts() {
        driver.findElement(addBackpackButton).click();
        driver.findElement(addBikeLightButton).click();
    }

    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}