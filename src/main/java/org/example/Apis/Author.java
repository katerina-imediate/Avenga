package org.example.Apis;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Author {

    private static String GET_AUTHORS_ENDPOINT = "Authors/";
    private static String GET_AUTHORS_BOOKS_ENDPOINT = "Authors/authors/books/";


    public static Response getAuthors() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_AUTHORS_ENDPOINT);
    }

    public static Response getAuthorsBooks(Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_AUTHORS_BOOKS_ENDPOINT + id);
    }
    public static Response postAuthor(String requestBody) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(GET_AUTHORS_ENDPOINT);
    }


    public static Response getAuthorId(Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_AUTHORS_ENDPOINT + id);
    }

    public static Response putAuthor(String requestBody, Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(GET_AUTHORS_ENDPOINT + id);
    }


    public static Response deleteAuhtor(Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete(GET_AUTHORS_ENDPOINT + id);
    }

    public static Response invalidPutAuthors(String requestBody) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(GET_AUTHORS_ENDPOINT);
    }
}
