package utils;

public class PayloadUtils {

    public static String getPetPayload() {

        String payload = "{\n" +
                "  \"id\": 555,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"Tuzik\"\n" +
                "  },\n" +
                "  \"name\": \"Bobik\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://s3.amazon.com\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"sdet pro\"\n" +
                "}";
        return payload;

    }

    public static String getSlackPayload(String message) {

        String slackPayload = "{\n" +
                "    \"channel\": \"C044QH2SS3U\",\n" +
                "    \"text\": \""+message+"\"\n" +
                "}";
        return slackPayload;
    }

}
