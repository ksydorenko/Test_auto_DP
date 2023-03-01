package lab3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Map;


public class UserTest {
    private static final String baseurl = "https://petstore.swagger.io/v2";

    private static final String USER = "/user",
                                USER_USERNAME = USER + "/{username}",
                                USER_LOGIN = USER + "/login",
                                USER_LOGOUT = USER + "/logout";

    private String username;
    private String firstName;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseurl;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void verifyLoginAction() {
        Map<String, ?> body = Map.of(
                "username", "MariiaLarykova",
                "password", "122m-22-2.14"
        );
        Response response = given().body(body).get(USER_LOGIN);
        response.then().statusCode(HttpStatus.SC_OK);
        RestAssured.requestSpecification
                .sessionId(response.jsonPath()
                        .get("message")
                        .toString()
                        .replaceAll("[^0-9]", ""));
    }

    @AfterClass
    public void tearDown() {
        System.out.println("After Class setup ...");
    }
}
