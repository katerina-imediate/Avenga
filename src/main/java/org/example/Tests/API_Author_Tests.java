package org.example.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.Apis.Author;
import org.example.Utils.Env;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;


public class API_Author_Tests {
    private static String requestBody = "{\n" +
            "  \"id\": \"30\",\n" +
            "  \"idBook\": \"6\",\n" +
            "  \"firstName\": \"George\",\n" +
            "  \"lastName\": \"Orwell\"\n}" ;

    private static String putRequest = "{\n" +
            "  \"id\": \"30\",\n" +
            "  \"idBook\": \"6\",\n" +
            "  \"firstName\": \"George\",\n" +
            "  \"lastName\": \"Orwell\"\n}" ;

    private static String invalidJson = "{\n" +
            "  \"id\": \"30\",\n" +
            "  \"idBook\": \"6\",\n" +
//            "  \"firstName\": \"George\",\n" +
            "  \"lastName\": \"Orwell\"\n}" ;
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Env.FAKEREST;
    }

    @Test
    public void getAuthorsTest() {
        Response response = Author.getAuthors();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void postAuthorTest() {
        Response response = Author.postAuthor(requestBody);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);

        response.then().assertThat().body(
        "id", is(30), "idBook", is(6), "firstName", is("George"),
                 "lastName", is("Orwell"));
    }

    @Test
    public void setInvalidJsonTest() {
        Response response = Author.invalidPutAuthors(invalidJson);
        response.then().assertThat().statusCode(405);
    }

    @Test
    public void putBookTest() {
        Response response = Author.putAuthor(putRequest, 4);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        response.then().assertThat().body(
                "id", is(30), "idBook", is(6), "firstName", is("George"),
                "lastName", is("Orwell"));
    }
    @Test
    public void notAllowedTest() {
        Response response = Author.invalidPutAuthors(putRequest);
        response.then().assertThat().statusCode(405);
    }

    @Test
    public void getAuthorsBooksTest() {
        Response response = Author.getAuthorsBooks(5);
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void getAuthorIdTest() {
        Response response = Author.getAuthorId(5);
        response.then().assertThat().body("id", is(5));
    }

    @Test
    public void deleteAuthorTest() {
        Response response = Author.deleteAuhtor(56);
        response.then().assertThat().statusCode(200);
    }
}