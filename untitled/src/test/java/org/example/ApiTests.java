package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ApiTests {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getPostReturns200() {
        Response response = RestAssured.get("/posts/1");
        Assert.assertEquals(response.getStatusCode(), 200, "Ожидается статус-код 200");
    }

    @Test
    public void createPostReturns201() {
        Post post = new Post();
        post.setTitle("My first post");
        post.setBody("Learning REST Assured");
        post.setUserId(1);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("/posts");

        Assert.assertEquals(response.getStatusCode(), 201, "Ожидается статус-код 201");

        Post createdPost = response.as(Post.class);
        Assert.assertEquals(createdPost.getTitle(), post.getTitle(), "Заголовок ответа должен совпадать с отправленным");
    }
}
