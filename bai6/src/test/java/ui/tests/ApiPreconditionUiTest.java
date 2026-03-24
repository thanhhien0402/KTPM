package ui.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.base.BaseUiTest;
import ui.pages.InventoryPage;
import ui.pages.LoginPage;

import static io.restassured.RestAssured.given;

public class ApiPreconditionUiTest extends BaseUiTest {

    private boolean apiPreconditionPassed = false;
    private String token;

    @BeforeMethod(alwaysRun = true)
    public void setupApiPrecondition() {
        Response response = given()
                .spec(requestSpec)
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .when()
                .post("/login");

        apiPreconditionPassed = response.statusCode() == 200;
        token = response.jsonPath().getString("token");

        System.out.println("API token = " + token);

        Assert.assertTrue(apiPreconditionPassed,
                "API login thất bại, không được chạy tiếp UI test");
    }

    @Test(description = "UI login chỉ chạy khi API precondition pass")
    public void testLoginUiAfterApiPrecondition() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"),
                "URL phải chứa inventory");
        Assert.assertEquals(getDriver().getTitle(), "Swag Labs",
                "Title phải là Swag Labs");
        Assert.assertTrue(inventoryPage.isLoaded(),
                "Trang inventory phải hiển thị");
    }
}