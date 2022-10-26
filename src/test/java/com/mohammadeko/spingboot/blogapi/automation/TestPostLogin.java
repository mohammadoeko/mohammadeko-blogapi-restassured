package com.mohammadeko.spingboot.blogapi.automation;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostLogin {

    String endpoint = "http://localhost:8080/api/auth/signin";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjY2ODIwMDY1LCJleHAiOjE2NjY4MjM2NjV9.a4eqYmhz2s7BWdlRAtCU-CDvdinjAwAtmHMptU0aqLnAzkaNKkucuQWck2G5UlhyNP5aGjJz4D7Ax7qHCn7DfQ";

    @Test
    public void testPostLogin() {
        JSONObject request = new JSONObject();

        request.put("usernameOrEmail", "mohammadeko");
        request.put("password", "password");
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void validasiAccessToken() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://localhost:8080/api/users/me")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("mohammad eko"));
    }
}
