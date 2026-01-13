package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutRequestTest extends BaseTest {

    @Test
    void putRequest_shouldReturnRequestBody() {

        String requestBody = "{"
                + "\"name\": \"Artem\","
                + "\"job\": \"QA\""
                + "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Artem"))
                .body("json.job", equalTo("QA"));
    }
}
