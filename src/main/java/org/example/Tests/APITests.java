package org.example.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Apis.Book;
import org.example.Utils.Env;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


public class APITests {
    private static String requestBody = "{\n" +
            "  \"id\": \"5\",\n" +
            "  \"idBook\": \"5\",\n" +
            "  \"url\": \"test1\" \n}";

    private static String putRequest = "{\n" +
            "  \"id\": \"6\",\n" +
            "  \"idBook\": \"6\",\n" +
            "  \"url\": \"updated_id_katerina\" \n}";

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
        response.then().assertThat().body("url", is("test1"), "id", is(5), "idBook", is(5));

    }

    @Test
    public void putBookTest() {
        Response response = Book.postBook(putRequest);
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("url", is("updated_id_katerina"), "id", is(6), "idBook", is(6));
    }

    @Test
    public void getBookIdTest() {
        Response response = Book.getBookId(5);
        response.then().assertThat().body("id", is(5));
    }

    @Test
    public void deleteBookTest() {

        Response response = Book.deleteBook(609);
        response.then().assertThat().statusCode(200);
    }
}
