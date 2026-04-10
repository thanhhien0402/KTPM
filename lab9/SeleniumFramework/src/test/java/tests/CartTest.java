package tests;

import framework.base.BaseTest;
import framework.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart(){

        LoginPage login = new LoginPage(getDriver());

        InventoryPage inventory =
                login.login("standard_user","secret_sauce");

        inventory.addFirstItemToCart();

        Assert.assertEquals(
                inventory.getCartItemCount(),
                1
        );
    }
}