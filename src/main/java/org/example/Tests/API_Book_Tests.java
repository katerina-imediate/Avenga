package org.example.Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.Apis.Book;
import org.example.Utils.Env;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.is;


public class API_Book_Tests {
    private static String requestBody = "{\n" +
            "  \"id\": \"300\",\n" +
            "  \"title\": \"katerina\",\n" +
            "  \"description\": \"katerina_description\",\n" +
            "  \"pageCount\": \"30\",\n" +
            "  \"excerpt\": \"string\",\n" +
            "  \"publishDate\": \"2025-09-28T08:02:38.042Z\"\n}" ;

    private static String putRequest = "{\n" +
            "  \"id\": \"30\",\n" +
            "  \"title\": \"katerina\",\n" +
            "  \"description\": \"katerina_description\",\n" +
            "  \"pageCount\": \"50\",\n" +
            "  \"excerpt\": \"string\",\n" +
            "  \"publishDate\": \"2025-09-28T08:02:38.042Z\"\n}" ;

    private static String invalidJson = "{\n" +
            "  \"id\": \"300\",\n" +
            "  \"title\": \"katerina\",\n" +
            "  \"description\": \"katerina_description\",\n" +
            "  \"pageCount\": \"30\",\n" +
//            "  \"excerpt\": \"string\",\n" +
            "  \"publishDate\": \"2025-09-28T08:02:38.042Z\", \n}" ;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = Env.FAKEREST;
    }

    @Test
    public void getBooksTest() {
        Response response = Book.getBooks();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void postBookTest() {
        Response response = Book.postBook(requestBody);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);

        response.then().assertThat().body(
        "id", is(300), "title", is("katerina"), "description", is("katerina_description"),
                "pageCount", is(30), "excerpt", is("string"),"publishDate", is("2025-09-28T08:02:38.042Z"));
    }

    @Test
    public void setInvalidJsonTest() {
        Response response = Book.postBook(invalidJson);
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void putBookTest() {
        Response response = Book.putBook(putRequest, 4);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType(ContentType.JSON);
        response.then().assertThat().body(
                "id", is(30), "title", is("katerina"), "description", is("katerina_description"),
                "pageCount", is(50), "excerpt", is("string"),"publishDate", is("2025-09-28T08:02:38.042Z"));
    }
    @Test
    public void notAllowedTest() {
        Response response = Book.invalidPutBook(putRequest);
        response.then().assertThat().statusCode(405);
    }

    @Test
    public void getBookIdTest() {
        Response response = Book.getBookId(5);
        response.then().assertThat().body("id", is(5));
    }

    @Test
    public void deleteBookTest() {

        Response response = Book.deleteBook(56);
        response.then().assertThat().statusCode(200);
    }
}