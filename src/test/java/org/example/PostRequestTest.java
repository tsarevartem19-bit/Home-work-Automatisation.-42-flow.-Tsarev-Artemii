package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestTest extends BaseTest {

    @Test
    void postRequest_shouldReturnRequestBody() {

        String requestBody = "{"
                + "\"name\": \"Artem\","
                + "\"job\": \"QA\""
                + "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("json.name", equalTo("Artem"))
                .body("json.job", equalTo("QA"));
    }
}
