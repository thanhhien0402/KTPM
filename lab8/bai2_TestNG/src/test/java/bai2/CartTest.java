package bai2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest {

    @Test(groups = {"smoke", "regression"}, description = "Them san pham vao gio hang")
    public void testAddToCart() {
        System.out.println("CartTest - testAddToCart");
        Assert.assertTrue(true, "Them san pham vao gio hang that bai");
    }

    @Test(groups = {"regression"}, description = "Xoa san pham khoi gio hang")
    public void testRemoveFromCart() {
        System.out.println("CartTest - testRemoveFromCart");
        Assert.assertTrue(true, "Xoa san pham khoi gio hang that bai");
    }
}