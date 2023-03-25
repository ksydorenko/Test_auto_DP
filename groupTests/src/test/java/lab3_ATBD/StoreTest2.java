package lab3_ATBD;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoreTest2 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetOrderById() {
        int orderId = 10;

        given()
                .when()
                .get("/store/order/{orderId}", orderId)
                .then()
                .statusCode(200)
                .body("id", equalTo(orderId))
                .body("petId", equalTo(1))
                .body("quantity", equalTo(1))
                .body("status", equalTo("placed"));
    }
}