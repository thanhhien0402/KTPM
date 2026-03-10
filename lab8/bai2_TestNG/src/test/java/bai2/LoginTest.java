package bai2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test(groups = {"smoke", "regression"}, description = "Dang nhap thanh cong")
    public void testLoginSuccess() {
        System.out.println("LoginTest - testLoginSuccess");
        Assert.assertTrue(true, "Dang nhap thanh cong that bai");
    }

    @Test(groups = {"regression"}, description = "Dang nhap sai mat khau")
    public void testLoginWrongPassword() {
        System.out.println("LoginTest - testLoginWrongPassword");
        Assert.assertTrue(true, "Thong bao loi dang nhap sai khong dung");
    }
}