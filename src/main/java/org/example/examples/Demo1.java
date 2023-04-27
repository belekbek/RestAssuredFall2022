package org.example.examples;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Demo1 {
    public static void main(String[] args) {
        final String  BASE_URL = "https://gorest.co.in/public/v2/";

        RequestSpecification requestSpecification = RestAssured.given();  //чтобы передавать параметр Header
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "Bearer 8361c98232e092f5ead95d935dc671e555490219b0096f3f647c3c3ec2f84cd2");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);

        Response response = requestSpecification // чтобы получить ответы
                .request(Method.GET, "users");

        Assert.assertEquals(200, response.getStatusCode());

    }

}
