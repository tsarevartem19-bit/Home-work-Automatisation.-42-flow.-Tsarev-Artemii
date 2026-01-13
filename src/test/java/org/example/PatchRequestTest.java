package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatchRequestTest extends BaseTest {

    @Test
    void patchRequest_shouldReturnRequestBody() {

        String requestBody = "{"
                + "\"status\": \"updated\""
                + "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.status", equalTo("updated"));
    }
}
