package post;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.ConfigReader;
import utils.PayloadUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Slack {

    // SENDING A MESSAGE TO SLACK WITH POST
    @Test
    public void sendMessageTest() {

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(PayloadUtils.getSlackPayload("Kuba: bla bla")).
                // this body is overloaded method
                header(ConfigReader.readProperty("authSlack"), ConfigReader.readProperty("tokenSlack"))
                .when().post().then().statusCode(200).extract().response();

    }

    @Test
    public void validatingSlackText() {

//        RestAssured.baseURI = "https://slack.com";
//        RestAssured.basePath = "api/conversations.history?channel=C044QH2SS3U";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header(ConfigReader.readProperty("authSlack"), ConfigReader.readProperty("tokenSlack"))
                .when().get("https://slack.com/api/conversations.history?channel=C044QH2SS3U").then().statusCode(200).extract().response();

        Map<String, Object> slackResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> list = (List<Map<String, Object>>) slackResponse.get("messages");

        response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header(ConfigReader.readProperty("authSlack"), ConfigReader.readProperty("tokenSlack"))
                .when().get("https://slack.com/api/conversations.history?channel=C044QH2SS3U").then().
                statusCode(200).extract().response();

        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            if (map.get("text").equals("Kuba: bla bla")){
                Assert.assertEquals("1664819148.670619", map.get("ts"));
            }
        }
        //1664818977.732599

    }

    @Test
    public void slackMessageTest() throws IOException {

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        SlackPojo slackPojo = new SlackPojo();
        slackPojo.setChannel("C044QH2SS3U");
        slackPojo.setText("Kuba: Sending a message from Pojo");

        File file = new File("src/test/resources/slackMessage.json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, slackPojo);

        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(slackPojo)
                .header(ConfigReader.readProperty("authSlack"), ConfigReader.readProperty("tokenSlack"))
                .when().post()
                .then().statusCode(200)
                .body("ok", Matchers.equalTo(true)).extract().response();

    }

    //another way to serialize
    @Test
    public void sendSlackMessage(){
        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";

        File file = new File("src/test/resources/slackMessage.json");
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header(ConfigReader.readProperty("authSlack"), ConfigReader.readProperty("tokenSlack"))
                .body(file).when().post().then().statusCode(200).and()
                .body("ok", Matchers.is(true));


    }


}
//1664818533.857719
