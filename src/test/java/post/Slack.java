package post;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import utils.PayloadUtils;

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
                header("Authorization", "Bearer xoxb-3471786148807-4185093481968-iOJahd3cCSTT7cPsAZuOVXiD")
                .when().post().then().statusCode(200).extract().response();

    }

    @Test
    public void validatingSlackText() {

//        RestAssured.baseURI = "https://slack.com";
//        RestAssured.basePath = "api/conversations.history?channel=C044QH2SS3U";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header("Authorization", "Bearer xoxb-3471786148807-4185093481968-iOJahd3cCSTT7cPsAZuOVXiD")
                .when().get("https://slack.com/api/conversations.history?channel=C044QH2SS3U").then().statusCode(200).extract().response();

        Map<String, Object> slackResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> list = (List<Map<String, Object>>) slackResponse.get("messages");

        response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .header("Authorization", "Bearer xoxb-3471786148807-4185093481968-iOJahd3cCSTT7cPsAZuOVXiD")
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

}
//1664818533.857719
