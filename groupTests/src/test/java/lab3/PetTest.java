package lab3;

// --------------------------------------
//
// DEVELOPED BY: Prytula Mykola 124m-22-1
//
// --------------------------------------


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
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;


public class PetTest {
    private static final String baseurl = "https://petstore.swagger.io/v2";

    private static final String PET =  "/pet",
            PET_ID = PET + "/{petId}",
            PET_FIND_BY_STATUS = PET + "/findByStatus";

    private static final int petId = 12345;
    private static final String petName = "Fido";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseurl;
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().build();
    }

    @Test
    public void verifyCreatePetAction() {
        Map<String, Object> pet = new HashMap<>();
        pet.put("id", petId);
        Map<String, Object> category = new HashMap<>();
        category.put("id", petId);
        category.put("name", "dogs");
        pet.put("category", category);
        pet.put("name", petName);
        pet.put("photoUrls", Collections.singletonList("string"));
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", petId);
        tag.put("name", "string");
        pet.put("tags", Collections.singletonList(tag));
        pet.put("status", "available");

        Response response = RestAssured.given().contentType("application/json").body(pet).post(PET);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "verifyCreatePetAction")
    public void verifyGetPetAction() {
        given().pathParam("petId", petId)
                .get(PET_ID)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("name", equalTo(petName));
    }

    @Test(dependsOnMethods = "verifyCreatePetAction")
    public void verifyUpdatePetAction() {
        Map<String, Object> pet = new HashMap<>();
        pet.put("id", petId);
        Map<String, Object> category = new HashMap<>();
        category.put("id", petId);
        category.put("name", "dogs");
        pet.put("category", category);
        pet.put("name", "Fido2");
        pet.put("photoUrls", Collections.singletonList("string"));
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", petId);
        tag.put("name", "string");
        pet.put("tags", Collections.singletonList(tag));
        pet.put("status", "available");

        Response response = RestAssured.given().contentType("application/json").body(pet).put(PET);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test(dependsOnMethods = "verifyUpdatePetAction")
    public void verifyDeletePetAction() {
        given().pathParam("petId", petId)
                .delete(PET_ID)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @AfterClass
    public void tearDown() {
        System.out.println("After Class setup ...");
    }
}