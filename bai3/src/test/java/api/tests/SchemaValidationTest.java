package api.tests;

import api.base.ApiBaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest extends ApiBaseTest {

    @Test(description = "Schema validation cho GET /api/users?page=2")
    public void testUserListSchema() {
        given()
                .spec(requestSpec)
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-list-schema.json"));
    }

    @Test(description = "Schema validation cho GET /api/users/2")
    public void testSingleUserSchema() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test(description = "Schema validation cho POST /api/users")
    public void testCreateUserSchema() {
        String requestBody = """
                {
                  "name": "Thanh Hien",
                  "job": "Tester"
                }
                """;

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"));
    }

    @Test(description = "Demo schema sai để quan sát test FAIL", enabled = false)
    public void testInvalidSchemaDemo() {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/invalid-user-schema.json"));
    }
}