package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.util.List;

public class GorestUsersSteps {
    RequestSpecification requestSpecification;
    Response response;
    @When("^all users are requested$")
    public void all_users_are_requested() throws Throwable {
        final String BASE_URL = "https://gorest.co.in/public/v2/";
        requestSpecification = RestAssured.given();  //чтобы передавать параметр Header
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.header("Authorization", "Bearer 8361c98232e092f5ead95d935dc671e555490219b0096f3f647c3c3ec2f84cd2");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.accept(ContentType.JSON);
        response = requestSpecification
                .request(Method.GET, "users");
        response.prettyPrint();
    }

    @Then("^a status code (\\d+) is returned$")
    public void a_status_code_is_returned(int arg1) throws Throwable {
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println(response.getStatusCode());
    }

    @Then("^(\\d+) users are returned$")
    public void users_are_returned(int arg1) throws Throwable {
        JsonPath jsonPath = response.jsonPath();
        List<String> idUsers = jsonPath.getList("id");
        Assert.assertEquals(10, idUsers.size());
        System.out.println(idUsers);
    }
}
