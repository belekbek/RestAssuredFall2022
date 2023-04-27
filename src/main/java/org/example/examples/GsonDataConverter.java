package org.example.examples;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.pojo.User;
import org.junit.BeforeClass;
import org.junit.Test;

public class GsonDataConverter {
    Gson gson = new Gson();
    static RequestSpecification requestSpecification;
    static Response response;
    @BeforeClass
    public static void setup() {
        final String BASE_URL = "https://gorest.co.in/public/v2/";
        requestSpecification = RestAssured.given();  //чтобы передавать параметр Header
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "Bearer 8361c98232e092f5ead95d935dc671e555490219b0096f3f647c3c3ec2f84cd2");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
    }
    @Test
    public void testGsonDeserialization(){
        String payload = "{\n" +
                "        \"name\": \"Belek Baratov\",\n" +
                "        \"email\": \"belek21121123@doe.com\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";
        User user = gson.fromJson(payload, User.class);
        System.out.println(user.getName());
        System.out.println(user.getGender());
    }

    @Test
    public void testGsonSerialization(){
        User user = new User();
        user.setName("Elon Musk");
        user.setGender("Male");
        user.setEmail("elonbek@gmail.com");
        user.setStatus("active");
        String payload = gson.toJson(user);
//        System.out.println(payload);
        response = requestSpecification
                .body(payload)
                .request(Method.POST, "users");
        response.prettyPrint();
    }
}
