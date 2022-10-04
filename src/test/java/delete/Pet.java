package delete;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetDeletePojo;
import utils.PayloadUtils;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Pet {

    @Before
    public void setUp() {

        // another way to declare 'baseURI'
        baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

    }

    @Test
    public void deletePetTest() {
        // post request
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(PayloadUtils.getPetPayload())
                .when().post().then().statusCode(200).extract().response();


        //TODO add post response validation - validate name, status, id

        response = given().accept(ContentType.JSON).when().get("/555").then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("category"));
        System.out.println(jsonPath.getInt("id"));
        Assert.assertEquals("Bobik", jsonPath.getString("name"));
        Assert.assertEquals("sdet pro", jsonPath.getString("status"));
        Assert.assertEquals(555, jsonPath.getInt("id"));


//        //TODO add post response validation - validate name, status, id
//
        response = given().accept(ContentType.JSON).when().delete("/20").then()
                .statusCode(200).extract().response();

        PetDeletePojo parsedDeletePetResponse = response.as(PetDeletePojo.class);

        Assert.assertEquals(200, parsedDeletePetResponse.getCode());

        response = given().accept(ContentType.JSON).when().get("/20").then().statusCode(404)
                .extract().response();

        Map<String, Object> deserializedGetResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        // code below is casting in 3 ways. Pay more attention to it.
        String expectedMessage = "Pet not found";
        String actualMessage = String.valueOf(deserializedGetResponse.get("message"));
        // String actualMessage = deserializedGetResponse.get("message").toString();
        //String actualMessage = (String) deserializedGetResponse.get("message");
        Assert.assertEquals(expectedMessage, actualMessage);


    }

}
