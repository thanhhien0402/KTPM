package bai2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest {

    @Test(groups = {"smoke", "regression"}, description = "Thanh toan thanh cong")
    public void testCheckoutSuccess() {
        System.out.println("CheckoutTest - testCheckoutSuccess");
        Assert.assertTrue(true, "Thanh toan that bai");
    }

    @Test(groups = {"regression"}, description = "Kiem tra thong tin bat buoc khi checkout")
    public void testCheckoutRequiredFields() {
        System.out.println("CheckoutTest - testCheckoutRequiredFields");
        Assert.assertTrue(true, "Kiem tra field bat buoc that bai");
    }
}