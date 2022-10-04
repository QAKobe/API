package post;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtils;

import java.util.Map;

public class Pet {

    @Test
    public void createPetTest() {

        //https://petstore.swagger.io/v2/pet
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";
        Response response = RestAssured.given().accept("application/json").contentType("application/json")
                .body(PayloadUtils.getPetPayload()).when().post().then().statusCode(200).extract().response();

        PetPojo deserializedResponse = response.as(PetPojo.class);

        Assert.assertEquals("hatiko", deserializedResponse.getName());

        response = RestAssured.given().accept("application/json").when().
                get("/10").then().statusCode(200).extract().response();
        //System.out.println(response);
        deserializedResponse = response.as(PetPojo.class);
        System.out.println(deserializedResponse.getId());

    }

}
