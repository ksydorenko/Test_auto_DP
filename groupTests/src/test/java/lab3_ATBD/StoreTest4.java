package lab3_ATBD;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoreTest4 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testGetInventory() {

        given()
                .when()
                .get("/store/inventory")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("pets", notNullValue())
                .body("pets", hasKey("available"))
                .body("pets", hasKey("pending"));
    }
}