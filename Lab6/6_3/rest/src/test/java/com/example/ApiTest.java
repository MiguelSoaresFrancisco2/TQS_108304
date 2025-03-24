package com.example;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    static {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    void testTodosEndpointIsAvailable() {
        given().
        when().
            get("/todos").
        then().
            statusCode(200);
    }

    @Test
    void testTodo4HasCorrectTitle() {
        given().
        when().
            get("/todos/4").
        then().
            statusCode(200).
            body("title", equalTo("et porro tempora"));
    }

    @Test
    void testTodosContainIds198And199() {
        given().
        when().
            get("/todos").
        then().
            statusCode(200).
            body("id", hasItems(198, 199));
    }

    @Test
    void testTodosResponseTimeIsUnder2Seconds() {
        given().
        when().
            get("/todos").
        then().
            time(lessThan(2000L));
    }
}
