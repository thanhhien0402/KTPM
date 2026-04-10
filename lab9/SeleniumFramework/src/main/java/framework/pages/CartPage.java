package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart_item")
    private List<WebElement> items;

    @FindBy(css = ".cart_item button")
    private List<WebElement> removeButtons;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {

        return items.size();
    }

    public CartPage removeFirstItem() {

        if (!removeButtons.isEmpty()) {
            waitAndClick(removeButtons.get(0));
        }

        return this;
    }
}