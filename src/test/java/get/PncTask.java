package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PncTask {


    @Test
    public void test1() {

        String desiredCountry = "Kyrgyzstan";

        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "/v3.1/all";

        Response response = given().accept(ContentType.JSON).when()
                .get().then().statusCode(200).extract().response();

        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (int i = 0; i < 1; i++) {
            for (Map<String, Object> k : parsedResponse) {
                Map<String, Object> name = (Map<String, Object>) k.get("name");
                if (desiredCountry.equalsIgnoreCase((String) name.get("common"))) {
                    List<String> capital = (List<String>) k.get("capital");
                    Assert.assertEquals("Bishkek", capital.get(i));
                    System.out.println(capital);
                    break;
                }
            }
        }
    }
}




