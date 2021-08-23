package testPetShop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.Category;
import model.Pet;
import model.Tag;
import org.junit.jupiter.api.*;
import utils.Log;
import utilsAPI.PetShopApiSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.CoreMatchers.equalTo;
import static utilsAPI.Status.AVAILABLE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPetStore {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static File jsonSchema = new File("src/test/resources/json/petJsonSchema.json");

    @Test
    @Order(2)
    public void testGetRequestPetStore() throws JsonProcessingException {
        Response response = given()
                .spec(PetShopApiSpecification.getRequestSpecification())
                .when()
                .get("12");

        response.prettyPrint();
        String responseBody = response.getBody().asString();
        Pet pet = OBJECT_MAPPER.readValue(responseBody, Pet.class);
        System.out.println(pet);
    }

    @Test
    @Order(1)
    public void testAddNewPet() {
        Tag tag = new Tag(1285, "Doberman");
        Category category = new Category(2, "Dog");
        String imageURL = "https://www.1001dog.com/wp-content/uploads/2016/07/1107a-142.jpg";
        Pet pet = new Pet(111, category, "Sugar", new ArrayList<>(Collections.singletonList(imageURL)), new ArrayList<>(Collections.singletonList(tag)), AVAILABLE);

        String jsonBody = "";
        try {
            jsonBody = OBJECT_MAPPER.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            Log.error("JsonBody does not created", e);
        }

        given()
                .spec(PetShopApiSpecification.getRequestSpecification())
                .when()
                .body(jsonBody)
                .post()
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    @Order(3)
    public void testUpdatePet() {
        Tag tag = new Tag(1285, "Doberman");
        Category category = new Category(2, "Dog");
        String imageURL = "https://www.1001dog.com/wp-content/uploads/2016/07/1107a-142.jpg";
        Pet pet = new Pet(12, category, "Lilu", new ArrayList<>(Collections.singletonList(imageURL)), new ArrayList<>(Collections.singletonList(tag)), AVAILABLE);

        String jsonBody = "";
        try {
            jsonBody = OBJECT_MAPPER.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            Log.error("JsonBody does not created", e);
        }

        given()
                .spec(PetShopApiSpecification.getRequestSpecification())
                .when()
                .body(jsonBody)
                .put()
                .then()
                .assertThat()
                .body("name", equalTo("Lilu"));
    }

    @Test
    @Order(4)
    public void testDeletePet() {

        given().spec(PetShopApiSpecification.getRequestSpecification())
                .when()
                .delete("111")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
