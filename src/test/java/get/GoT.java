package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import lombok.var;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoT {

    @Test
    public void GoTTest(){

        RestAssured.baseURI = "https://api.got.show";
        RestAssured.basePath = "api/show/characters";

       Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).extract().response();
       List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
       });

       Map<String, List<String>> charactersByHouseMap = (Map<String, List<String>>) parsedResponse;
        for (int i = 0; i < parsedResponse.size(); i++) {

            Map<String, Object> map = parsedResponse.get(i);
            if (map.get("house").equals("House Stark")){
                System.out.println(map.get("name"));
            }

        }


    }

}
