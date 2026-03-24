package api.test;

import api.base.ApiBaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserGetTest extends ApiBaseTest {

    @Test(description = "Test 1: GET /api/users?page=1")
    public void testGetUsersPage1() {
        given()
                .spec(requestSpec)
                .queryParam("page", 1)
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("page", equalTo(1))
                .body("total_pages", greaterThan(0))
                .body("data.size()", greaterThanOrEqualTo(1));
    }

    @Test(description = "Test 2: GET /api/users?page=2")
    public void testGetUsersPage2AndValidateUserFields() {
        given()
                .spec(requestSpec)
                .queryParam("page", 2)
                .when()
                .get("/api/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.size()", greaterThan(0))
                .body("data.id", everyItem(notNullValue()))
                .body("data.email", everyItem(not(blankOrNullString())))
                .body("data.first_name", everyItem(not(blankOrNullString())))
                .body("data.last_name", everyItem(not(blankOrNullString())))
                .body("data.avatar", everyItem(not(blankOrNullString())));
    }

    @Test(description = "Test 3: GET /api/users/3")
    public void testGetSingleUserById3() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users/3")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("data.id", equalTo(3))
                .body("data.email", containsString("@reqres.in"))
                .body("data.first_name", not(blankOrNullString()));
    }

    @Test(description = "Test 4: GET /api/users/9999")
    public void testGetUserNotFound() {
        given()
                .spec(requestSpec)
                .when()
                .get("/api/users/9999")
                .then()
                .statusCode(404)
                .body(equalTo("{}"));
    }
}