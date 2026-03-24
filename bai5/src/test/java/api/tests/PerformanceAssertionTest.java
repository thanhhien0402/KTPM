package api.tests;

import api.base.ApiBaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PerformanceAssertionTest extends ApiBaseTest {

    @DataProvider(name = "apiPerformanceData")
    public Object[][] apiPerformanceData() {
        return new Object[][]{
                {"GET", "/users", 200, 2000L, "LIST_USERS"},
                {"GET", "/users/2", 200, 1500L, "GET_SINGLE_USER"},
                {"POST", "/users", 201, 3000L, "CREATE_USER"},
                {"POST", "/login", 200, 2000L, "LOGIN"},
                {"DELETE", "/users/2", 204, 1000L, "DELETE_USER"}
        };
    }

    @Test(dataProvider = "apiPerformanceData",
            description = "Kiểm thử SLA cho 5 API chính bằng 1 method test")
    public void testSlaMonitoring(String method,
                                  String endpoint,
                                  int expectedStatus,
                                  long maxMs,
                                  String scenario) {

        Response response = callApi(method, endpoint, maxMs, scenario);

        long actualMs = response.time();
        System.out.println("[SLA] " + method + " " + endpoint + " -> " + actualMs + "ms");

        Assert.assertEquals(response.statusCode(), expectedStatus,
                "Sai status code cho " + method + " " + endpoint);

        Assert.assertTrue(actualMs < maxMs,
                "Response time " + actualMs + "ms vượt SLA " + maxMs + "ms");

        switch (scenario) {
            case "LIST_USERS":
                response.then().body("data.size()", greaterThanOrEqualTo(1));
                break;

            case "GET_SINGLE_USER":
                response.then().body("data.id", equalTo(2));
                break;

            case "CREATE_USER":
                response.then()
                        .body("id", notNullValue())
                        .body("id", not(emptyString()));
                break;

            case "LOGIN":
                response.then()
                        .body("token", notNullValue())
                        .body("token", not(emptyString()));
                break;

            case "DELETE_USER":
                Assert.assertTrue(response.getBody().asString().isEmpty(),
                        "DELETE phải trả body rỗng");
                break;

            default:
                throw new IllegalArgumentException("Scenario không hỗ trợ: " + scenario);
        }
    }

    @Step("Gọi {method} {endpoint} - SLA: {maxMs}ms")
    public Response callApi(String method, String endpoint, long maxMs, String scenario) {
        switch (method.toUpperCase()) {
            case "GET":
                return given()
                        .spec(requestSpec)
                        .when()
                        .get(endpoint);

            case "POST":
                return given()
                        .spec(requestSpec)
                        .body(getRequestBody(scenario))
                        .when()
                        .post(endpoint);

            case "DELETE":
                return given()
                        .spec(requestSpec)
                        .when()
                        .delete(endpoint);

            default:
                throw new IllegalArgumentException("Method không hỗ trợ: " + method);
        }
    }

    @Test(description = "Chạy cùng API 10 lần liên tiếp và log average/min/max response time")
    public void testSimpleMonitoring10Runs() {
        String endpoint = "/users/2";
        int runs = 10;

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long total = 0;

        for (int i = 1; i <= runs; i++) {
            Response response = given()
                    .spec(requestSpec)
                    .when()
                    .get(endpoint);

            long current = response.time();
            total += current;

            if (current < min) {
                min = current;
            }
            if (current > max) {
                max = current;
            }

            System.out.println("[Monitoring] Lần " + i + ": GET " + endpoint + " -> " + current + "ms");
            Assert.assertEquals(response.statusCode(), 200);
        }

        double average = (double) total / runs;

        System.out.println("===== SIMPLE MONITORING RESULT =====");
        System.out.println("API: GET " + endpoint);
        System.out.println("Runs: " + runs);
        System.out.println("Average: " + average + "ms");
        System.out.println("Min: " + min + "ms");
        System.out.println("Max: " + max + "ms");
    }

    private Map<String, String> getRequestBody(String scenario) {
        Map<String, String> body = new HashMap<>();

        switch (scenario) {
            case "CREATE_USER":
                body.put("name", "Thanh Hien");
                body.put("job", "Tester");
                break;

            case "LOGIN":
                body.put("email", "eve.holt@reqres.in");
                body.put("password", "cityslicka");
                break;
        }

        return body;
    }
}