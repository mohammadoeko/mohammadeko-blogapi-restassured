package com.mohammadeko.spingboot.blogapi.automation;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPostAlbum {
    String endpoint = "http://localhost:8080/api/albums";
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjY2ODE3ODg1LCJleHAiOjE2NjY4MjE0ODV9.LZICK4M0sKih3drIqlyOwbaX32ahHngM7nVWeCkVjxk21YyF9lwe8AoyK2HPFMBkgXFal3iwm5YwiCPBNOg-8w";

    @Test
    public void testPostAlbum() {
        JSONObject request = new JSONObject();
        request.put("title", "Bootcamp SQA 6");
        System.out.println(request.toJSONString());
        given()
                .header("Authorization", "Bearer " + token)
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
    public void validasiPostAlbum() {
        given()
                .get("http://localhost:8080/api/users/mohammadeko/albums")
                .then()
                .statusCode(200)
                .body("content.id[0]", equalTo(4));
    }
}
