package api.tests;

import api.base.ApiBaseTest;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.notNullValue;

public class AuthorizationTest extends ApiBaseTest {

    @Test(description = "Login thành công -> trả về token")
    public void testLoginSuccess() {
        String token = given()
                .spec(requestSpec)
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("token", notNullValue())
                .body("token", not(emptyString()))
                .extract()
                .jsonPath()
                .getString("token");

        Assert.assertFalse(token.isEmpty(), "Token không được rỗng");
    }

    @Test(description = "Login thiếu password -> 400")
    public void testLoginMissingPassword() {
        given()
                .spec(requestSpec)
                .body("{\"email\":\"eve.holt@reqres.in\"}")
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("error", containsString("Missing password"));
    }

    @Test(description = "Login thiếu email -> 400")
    public void testLoginMissingEmail() {
        given()
                .spec(requestSpec)
                .body("{\"password\":\"cityslicka\"}")
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("error", containsString("Missing email or username"));
    }

    @Test(description = "Register thành công -> 200, có id và token")
    public void testRegisterSuccess() {
        given()
                .spec(requestSpec)
                .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"pistol\"}")
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue())
                .body("token", not(emptyString()));
    }

    @Test(description = "Register thiếu password -> 400")
    public void testRegisterMissingPassword() {
        given()
                .spec(requestSpec)
                .body("{\"email\":\"sydney@fife\"}")
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("error", containsString("Missing password"));
    }

    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][]{
                {"eve.holt@reqres.in", "cityslicka", 200, null},
                {"eve.holt@reqres.in", "", 400, "Missing password"},
                {"", "cityslicka", 400, "Missing email or username"},
                {"notexist@reqres.in", "wrongpass", 400, "user not found"},
                {"invalid-email", "pass123", 400, "user not found"}
        };
    }

    @Test(dataProvider = "loginScenarios", description = "Data-driven login scenarios")
    public void testLoginScenarios(String email, String password,
                                   int expectedStatus, String expectedError) {

        Map<String, String> body = new HashMap<>();

        if (!email.isEmpty()) {
            body.put("email", email);
        }

        if (!password.isEmpty()) {
            body.put("password", password);
        }

        ValidatableResponse response = given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .statusCode(expectedStatus);

        if (expectedError != null) {
            response.body("error", containsString(expectedError));
        } else {
            response.body("token", notNullValue());
            response.body("token", not(emptyString()));
        }
    }
}