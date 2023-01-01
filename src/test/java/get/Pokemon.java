package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import pojo.PokemonPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokemon {

    /*
    Construct request:
    1.define URL
    2.add header
    3.define the HTTP method
    Send Request
    Validate response status code
     */

    // idea of mapping
    @Test
    public void getPokemonTest() {

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon").then().statusCode(200)
                .log().all().extract().response();

        Map<String, Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(deserializeResponse);

        Assert.assertEquals(null, deserializeResponse.get("previous"));
        Assert.assertNull(deserializeResponse.get("previous"));

        //
        Object object = deserializeResponse.get("results"); //=> get() return type is Object, bc Map<String, Object> deserializeResponse
        List<Map<String, String>> list = (List<Map<String, String>>) object;// casting object to List<Map<String, String>>
        int count = list.size();
//        for (int i = 0; i < list.size(); i++) {
//
//            Map<String, String > map = list.get(i);
//            String name = map.get("name");
//            System.out.println(name);
//
//        }



        while (deserializeResponse.get("next")!= null){

            response =  RestAssured.given().header("Accept", "application/json").when()
                    .get((String) deserializeResponse.get("next")).then().statusCode(200)
                    .log().all().extract().response();

           deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
            });

           list = (List<Map<String, String>>) deserializeResponse.get("results");
           count += list.size();

            for (int i = 0; i < list.size(); i++) {

                Map<String, String> map = list.get(i);
                if (map.get("name").equalsIgnoreCase("pikachu")){
                    System.out.println(map.get("url"));
                    break;
                }
                }

            }
        System.out.println(deserializeResponse.get("name"));
        Assert.assertEquals(count, deserializeResponse.get("count"));


        }






    @Test
    public void pojoPokemon() {

        Response response = RestAssured.given().accept("application/json").when().get("https://pokeapi.co/api/v2/pokemon").then()
                .statusCode(200).extract().response();

        PokemonPojo deserializedResponse = response.as(PokemonPojo.class);
        Assert.assertEquals(1154, deserializedResponse.getCount());
        Assert.assertEquals("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20", deserializedResponse.getNext());
        Assert.assertEquals(20, deserializedResponse.getResults().size());
    }


}
