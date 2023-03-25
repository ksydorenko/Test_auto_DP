package lab3_ATBD;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StoreTest3 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testDeleteOrderById() {
        int orderId = 10;

        given()
                .when()
                .delete("/store/order/{orderId}", orderId)
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/store/order/{orderId}", orderId)
                .then()
                .statusCode(404);
    }
}