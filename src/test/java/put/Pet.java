package put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utils.PayloadUtils;

import java.util.Map;

public class Pet {

    @Test
    public void updatePetTest() {

        Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(PayloadUtils.getPetPayload()).when()
                .put("https://petstore.swagger.io/v2/pet").then()
                .statusCode(200).body("name", Matchers.equalTo("Bobik"))
                .and().body("status", Matchers.equalTo("sdet pro"))
                .body("category.name", Matchers.equalTo("Tuzik")).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String nameValue = jsonPath.getString("name");
        System.out.println("NameValue " + nameValue);

        int id = jsonPath.getInt("id");
        System.out.println("ID: " + id);

        Map<String, Object> map = jsonPath.getMap("category");
        System.out.println("Category map: " + map);

        Assert.assertEquals(555, id);
        Assert.assertEquals("Bobik", nameValue);



    }
}
