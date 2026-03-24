package api.tests;

import api.base.ApiBaseTest;
import api.models.CreateUserRequest;
import api.models.UserResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudTest extends ApiBaseTest {

    private static String createdUserId;
    private static String createdAtValue;

    @Test(description = "POST /api/users - tạo user mới")
    public void testCreateUser() {
        CreateUserRequest requestBody = new CreateUserRequest("Thanh Hien", "QA Engineer");

        UserResponse response =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec)
                        .statusCode(201)
                        .body("name", equalTo("Thanh Hien"))
                        .body("job", equalTo("QA Engineer"))
                        .body("id", notNullValue())
                        .body("createdAt", notNullValue())
                        .extract()
                        .as(UserResponse.class);

        createdUserId = response.getId();
        createdAtValue = response.getCreatedAt();

        Assert.assertNotNull(createdUserId, "ID không được null");
        Assert.assertNotNull(createdAtValue, "createdAt không được null");
    }

    @Test(description = "PUT /api/users/2 - cập nhật toàn bộ user")
    public void testUpdateUserWithPut() {
        CreateUserRequest requestBody = new CreateUserRequest("Thanh Hien", "Senior QA");

        UserResponse response =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                        .when()
                        .put("/users/2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .body("name", equalTo("Thanh Hien"))
                        .body("job", equalTo("Senior QA"))
                        .body("updatedAt", notNullValue())
                        .extract()
                        .as(UserResponse.class);

        Assert.assertNotNull(response.getUpdatedAt(), "updatedAt không được null");

        if (createdAtValue != null) {
            Assert.assertNotEquals(response.getUpdatedAt(), createdAtValue,
                    "updatedAt nên khác createdAt");
        }
    }

    @Test(description = "PATCH /api/users/2 - cập nhật một phần")
    public void testPatchUser() {
        Response response =
                given()
                        .spec(requestSpec)
                        .body("{\"job\":\"Lead QA\"}")
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(responseSpec)
                        .statusCode(200)
                        .body("job", equalTo("Lead QA"))
                        .body("updatedAt", notNullValue())
                        .extract()
                        .response();

        String updatedAt = response.jsonPath().getString("updatedAt");
        Assert.assertNotNull(updatedAt, "updatedAt không được null");
    }

    @Test(description = "DELETE /api/users/2 - xóa user")
    public void testDeleteUser() {
        given()
                .spec(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204)
                .body(emptyOrNullString());
    }

    @Test(description = "POST rồi lấy id để xác nhận")
    public void testCreateThenGetConfirm() {
        CreateUserRequest requestBody = new CreateUserRequest("Lan", "Tester");

        Response postResponse =
                given()
                        .spec(requestSpec)
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec)
                        .statusCode(201)
                        .body("name", equalTo("Lan"))
                        .body("job", equalTo("Tester"))
                        .body("id", notNullValue())
                        .extract()
                        .response();

        String newId = postResponse.jsonPath().getString("id");
        Assert.assertNotNull(newId, "ID sau khi tạo không được null");

        given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", notNullValue());
    }
}