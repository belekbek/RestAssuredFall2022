package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Demo2 {

    static String userId = "";
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
    public void test1UserDetails() {
        response = requestSpecification // чтобы получить ответы
                .request(Method.GET, "users");

        response = requestSpecification // чтобы получить ответы
                .request(Method.GET, "users/1218093");
        response.prettyPrint();
    }

        @Test()
    public void test2CreateUser(){
        String payload = "{\n" +
                "        \"name\": \"Belek Baratov\",\n" +
                "        \"email\": \"belek21121123@doe.com\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";
        requestSpecification.body(payload);
        response = requestSpecification
                .request(Method.POST, "users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        userId = jsonPath.getString("id");
        Assert.assertEquals(201, response.getStatusCode());
        System.out.println(userId);
    }

    @Test
    public void test3UpdateUserData(){
        String payload = "{\n" +
                "        \"name\": \"Belek1 Baratov\",\n" +
                "        \"email\": \"belek21124@doe.com\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";
        requestSpecification.body(payload);
        response = requestSpecification
                .request(Method.PATCH, "users/" + userId);
        response.prettyPrint();
    }

    static String user1Id = "";
    @Test
    public void test4FindUser() {
        requestSpecification.queryParam("name", "Belek Baratov");
        response = requestSpecification
                .request(Method.GET, "users");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        user1Id = (jsonPath.getString("id"));
        System.out.println(user1Id);
    }

    @Test
    public void test5DeleteUser(){
        String str[] = user1Id.substring(1,user1Id.length()-1).split(",");
        System.out.println(user1Id);
        System.out.println(Arrays.toString(str));
        for (String s : str){
            response = requestSpecification
                    .request(Method.DELETE, "users/" + s);
            System.out.println(s);
        }
        response.prettyPrint();
    }

}
