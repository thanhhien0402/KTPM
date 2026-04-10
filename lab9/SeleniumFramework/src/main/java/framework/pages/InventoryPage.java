package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(css = ".inventory_list")
    private WebElement inventoryList;

    @FindBy(css = ".inventory_item button")
    private List<WebElement> addButtons;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return inventoryList.isDisplayed();
    }

    public InventoryPage addFirstItemToCart() {

        waitAndClick(addButtons.get(0));

        return this;
    }

    public int getCartItemCount() {

        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage goToCart() {

        driver.findElement(By.className("shopping_cart_link")).click();

        return new CartPage(driver);
    }
}