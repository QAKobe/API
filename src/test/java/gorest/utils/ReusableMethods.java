package gorest.utils;

import gorest.pojos.GoRestPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utils.ConfigReader;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    final static String APPLICATION_JSON = "application/json";

    public static Response createUser() {

        RestAssured.baseURI = "https://gorest.co.in/public-api";
        RestAssured.basePath = "users";

        File createUserBody = new File("src/test/java/gorest/files/createUser.json");
        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).header(ConfigReader.readProperty("authGoForest"), ConfigReader.readProperty("tokenGoForest"))
                .body(createUserBody)
                .when()
                .post().then().statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("code", Matchers.equalTo(201))
                .extract().response();
        System.out.println(response);
        return response;
    }

    public static Response getUser(String userId) {
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        RestAssured.basePath = "/users/" + userId;

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header(ConfigReader.readProperty("authGoForest"), "tokenGoForest")
                .when()
                .get().then().statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("code", Matchers.equalTo(200))
                .extract().response();
        System.out.println(response);
        return response;


    }
    public static Response updateUser(String userId) {
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        RestAssured.basePath = "/users/" + userId;

        File createUserBody = new File("src/test/java/gorest/files/updateBody.json");
        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(createUserBody)
                .header(ConfigReader.readProperty("authGoForest"), ConfigReader.readProperty("tokenGoForest"))
                .when()
                .put().then().statusCode(200)
                .contentType(APPLICATION_JSON)
                .body("code", Matchers.equalTo(200))
                .extract().response();
        System.out.println(response);
        return response;
    }

    @Test
    public void endToEndTest() {
        //deserialize createUser() response
        GoRestPojo pojo = createUser().as(GoRestPojo.class);

        //get the id from create user response
        Integer createUserId = (Integer) pojo.getData().get("id");

        //get the id from getUser response
        GoRestPojo getUserResponse = getUser(String.valueOf(createUserId)).as(GoRestPojo.class);

        Integer getUserId = (Integer) getUserResponse.getData().get("id");

        //verify that id from create user is the same as the id from get user
        Assert.assertEquals(createUserId, getUserId);
        System.out.println(createUserId);
        System.out.println(getUserId);

        //update user status from active to inactive
        Response updateUserMethodResponse = updateUser(""+createUserId);
        GoRestPojo updateUserResponse = updateUserMethodResponse.as(GoRestPojo.class);
        String updateUserStatus = (String) updateUserResponse.getData().get("status");

        GoRestPojo getUserResponse2 = getUser(""+createUserId).as(GoRestPojo.class);

        Assert.assertEquals(updateUserStatus, getUserResponse2.getData().get("status"));

        //get the user again




        //deserialize the body

    }




//4489
}
