package tests;

import framework.base.BaseTest;
import framework.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess(){

        LoginPage login = new LoginPage(getDriver());

        InventoryPage inventory = login.login(
                "standard_user",
                "secret_sauce"
        );

        Assert.assertTrue(inventory.isLoaded());
    }

    @Test
    public void testLoginFail(){

        LoginPage login = new LoginPage(getDriver());

        login.loginExpectingFailure(
                "standard_user",
                "wrong"
        );

        Assert.assertTrue(login.isErrorDisplayed());
    }
}