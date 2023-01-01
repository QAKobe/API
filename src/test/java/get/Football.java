package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Football {

    @Test
    public void footballTest() {

        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "/v2/competitions";

        Response response = given().accept(ContentType.JSON)
                .header(ConfigReader.readProperty("authFootball"), ConfigReader.readProperty("tokenFootball"))
                .when().get().then().and()
                .statusCode(200)
                .extract().response();

        JsonPath parsedResponse = response.jsonPath();
        List<Map<String, Object>> competitions = parsedResponse.getList("competitions");

        for (int i = 0; i < competitions.size(); i++) {

            Map<String, Object> countryMap = competitions.get(i);
            Map<String, Object> countryAreaMap = (Map<String, Object>) countryMap.get("area");
            if (countryAreaMap.get("name").equals("Australia")) {
                Assert.assertEquals("AUS", countryAreaMap.get("countryCode"));
            }
        }

        String countryCode = response.path("competitions.find {it.area.name == 'Australia'}.area.countryCode");
        Assert.assertEquals("AUS", countryCode);
        System.out.println(countryCode);


    }
}
