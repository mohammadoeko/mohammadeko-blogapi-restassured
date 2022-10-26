package com.mohammadeko.spingboot.blogapi.automation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestGetAlbum {

    String endpoint = "http://localhost:8080/api/users/mohammadeko/albums";

    @Test
    public void testStatusCode() {
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());

        int actual = response.getStatusCode();
        Assert.assertEquals(actual, 200);
    }

    @Test
    public void testValidaseGetAlbum() {
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("content.title[0]", equalTo("Bootcamp SQA 6"));
    }
}
