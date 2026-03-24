package api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class ApiBaseTest {

    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setupApiSpec() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .addHeader("x-api-key", "reqres_fb830d8fcaf1490fa8c8d22601ef18a7")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
    }
}