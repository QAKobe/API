package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class StarWars {

    @Test
    public void watchStarWars() {

        Response response = RestAssured.given().header("Accept", "application/json").when().get("https://swapi.dev/api/people")
                .then().statusCode(200).log().all().extract().response();

        Map<String, Object> starWarsMap = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(starWarsMap);

        // Object object = starWarsMap.get("results");
        List<Map<String, Object>> list = (List<Map<String, Object>>) starWarsMap.get("results");

//        for (int i = 0; i < list.size(); i++) {
//
//            Map<String, Object> map = list.get(i);
////            Object name = map.get("name"); // this will work too
////            System.out.println(name.toString());
//            System.out.println(map.get("name"));
//
//        }

        for (int i = 0; i < list.size(); i++) {

            Map<String, Object> map = list.get(i);
            if (list.get(i).containsValue("female")) {
                System.out.println(map.get("name"));
            }

        }

    }

}



