package api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.baseURI;

public class ApiBaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        baseURI = "https://reqres.in/api";

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addHeader("x-api-key", "reqres_fb830d8fcaf1490fa8c8d22601ef18a7")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }
}