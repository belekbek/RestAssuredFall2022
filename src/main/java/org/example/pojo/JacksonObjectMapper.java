package org.example.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

public class JacksonObjectMapper {
    ObjectMapper objectMapper = new ObjectMapper();
    XmlMapper xmlMapper = new XmlMapper();
    static RequestSpecification requestSpecification;
    static Response response;
    @BeforeClass
    public static void setup() {
        final String BASE_URL = "https://gorest.co.in/public/v2/";
        requestSpecification = RestAssured.given();  //чтобы передавать параметр Header
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "Bearer 8361c98232e092f5ead95d935dc671e555490219b0096f3f647c3c3ec2f84cd2");
        requestSpecification.contentType(ContentType.XML);
        requestSpecification.accept(ContentType.XML);
    }
    @Test
    public void jsonToJava() throws JsonProcessingException {
        String payload = "{\n" +
                "        \"name\": \"Belek Baratov\",\n" +
                "        \"email\": \"belek21121123@doe.com\",\n" +
                "        \"gender\": \"male\",\n" +
                "        \"status\": \"active\"\n" +
                "    }";
        User user = objectMapper.readValue(payload, User.class);
        System.out.println(user.getEmail());
    }

    @Test
    public void javaToXml() throws JsonProcessingException {
        User user = new User();
        user.setName("Elon Musk");
        user.setGender("Male");
        user.setEmail("elonbek1@gmail.com");
        user.setStatus("active");

        String xmlPayload = xmlMapper.writeValueAsString(user);
        response = requestSpecification
                .body(xmlPayload)
                .request(Method.POST, "users");
        response.prettyPrint();
    }
}
