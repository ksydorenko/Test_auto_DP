package lab3_ATBD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class StoreTest {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreateOrder() {
        String requestBody = "{\"id\": 10, \"petId\": 1, \"quantity\": 1, \"shipDate\": \"2023-03-23T16:41:41.291Z\", \"status\": \"placed\", \"complete\": true}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .body("id", equalTo(10))
                .body("petId", equalTo(1))
                .body("quantity", equalTo(1))
                .body("status", equalTo("placed"));
    }


}
