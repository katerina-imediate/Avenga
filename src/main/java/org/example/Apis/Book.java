package org.example.Apis;


import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Book {

    private static String GET_BOOKS_ENDPOINT = "CoverPhotos/";



    public static Response getBooks() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(GET_BOOKS_ENDPOINT);
    }


    public static Response postBook(String requestBody) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(GET_BOOKS_ENDPOINT);
    }


    public static Response getBookId(Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
//                .body(id)
                .when()
                .get(GET_BOOKS_ENDPOINT + id);
    }

    public static Response putBookTest(String requestBody, Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(GET_BOOKS_ENDPOINT + id);
    }


    public static Response deleteBook(Integer id) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete(GET_BOOKS_ENDPOINT + id);
    }
}
