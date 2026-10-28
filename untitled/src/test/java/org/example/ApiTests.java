package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {
    @Test
    public void getPostReturns200() {
        Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200, "Ожидается статус-код 200");
    }

    @Test
    public void createPostReturns201() {
        Post post = new Post();
        post.title = "My first post";
        post.body = "Learning REST Assured";
        post.userId = 1;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts");

        Assert.assertEquals(response.getStatusCode(), 201, "Ожидается статус-код 201");
    }
}
