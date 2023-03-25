package lab3_ATBD;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
        //зазначаємо посилання
        RestAssured.baseURI = baseurl;
        //зазначаємо тіло запиту
        RestAssured.defaultParser = Parser.JSON;
        //зазанчаємо спеціфікацію запиту
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void verifyLoginAction() {
        //формуємо тіло запиту
        Map<String, ?> body = Map.of(
                "username", "KaterynaSydorenko",
                "password", "124m-22-1.17"
        );
        Response response = given().body(body).get(USER_LOGIN);
        response.then().statusCode(HttpStatus.SC_OK);
        RestAssured.requestSpecification
                .sessionId(response.jsonPath()
                        .get("message")
                        .toString()
                        .replaceAll("[^0-9]", ""));
    }

    @Test(dependsOnMethods = "verifyLoginAction")
    public void verifyCreateAction() {
        username = Faker.instance().name().username();
        firstName = Faker.instance().harryPotter().character();
        Map<String, ?> body = Map.of(
                "username", username,
                "firstName", firstName,
                "lastName", Faker.instance().gameOfThrones().character(),
                "email", Faker.instance().internet().emailAddress(),
                "password", Faker.instance().internet().password(),
                "phone", Faker.instance().phoneNumber().phoneNumber(),
                "userStatus", Integer.valueOf("1")
        );
        given().body(body)
                .post(USER)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "verifyCreateAction")
    public void verifyGetAction() {
        given().pathParam("username", username)
                .get(USER_USERNAME)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("firstName", equalTo(firstName));
    }

    @Test(dependsOnMethods = "verifyGetAction")
    public void verifyDeleteAction() {
        given().pathParam("username", username)
                .delete(USER_USERNAME)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "verifyLoginAction", priority = 1)
    public void verifyLogoutAction() {
        given().get(USER_LOGOUT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("After Class setup ...");
    }

}
