package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteRequestTest extends BaseTest {

    @Test
    void deleteRequest_shouldReturnRequestBody() {

        String requestBody = "{"
                + "\"id\": 123"
                + "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json.id", equalTo(123));
    }
}