package ui.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.CartPage;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;

import static io.restassured.RestAssured.given;

public class FullFlowApiUiTest extends BaseUiTest {

    private boolean isApiAlive = false;

    @BeforeMethod(alwaysRun = true)
    public void checkApiHealth() {
        // ===== API CHECK =====
        // Gọi GET /api/users để xác nhận API còn hoạt động
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/users");

        isApiAlive = response.statusCode() == 200;
        System.out.println("isApiAlive = " + isApiAlive);
    }

    @Test(description = "Luồng tích hợp đầy đủ: API check -> UI login -> add 2 items -> verify cart")
    public void testFullIntegrationFlow() {
        // ===== API CHECK RESULT =====
        // Nếu API chết thì bỏ qua UI test
        if (!isApiAlive) {
            throw new SkipException("API reqres.in không sống, skip UI test");
        }

        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        // ===== UI ACTION =====
        // Mở trang login và đăng nhập bằng form thật
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        // ===== ASSERTION =====
        // Xác nhận đã vào inventory page
        Assert.assertTrue(inventoryPage.isLoaded(),
                "Phải vào được trang inventory sau login");

        // ===== UI ACTION =====
        // Thêm 2 sản phẩm vào giỏ
        inventoryPage.addTwoProducts();

        // ===== ASSERTION =====
        // Badge giỏ hàng phải là 2
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "2",
                "Badge giỏ hàng phải là 2");

        // ===== UI ACTION =====
        // Vào giỏ hàng
        inventoryPage.openCart();

        // ===== ASSERTION =====
        // Giỏ hàng phải có đúng 2 sản phẩm
        Assert.assertEquals(cartPage.getItemCount(), 2,
                "Giỏ hàng phải có đúng 2 sản phẩm");
    }
}