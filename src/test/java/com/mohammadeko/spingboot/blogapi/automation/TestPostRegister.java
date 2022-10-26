package com.mohammadeko.spingboot.blogapi.automation;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostRegister {

    String endpoint = "http://localhost:8080/api/auth/signup";

    @Test
    public void testPostRegister() {
        JSONObject request = new JSONObject();
        request.put("firstName", "Mohammad Eko");
        request.put("lastName", "Nur Fauzi");
        request.put("username", "mohammadeko");
        request.put("password", "password");
        request.put("email", "mohammadeko@email.com");
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void testalidasiRegisterID() {
        given()
                .get("http://localhost:8080/api/users/mohammadeko/profile")
                .then()
                .statusCode(200)
                .body("username", equalTo("mohammadeko"));
    }
}
