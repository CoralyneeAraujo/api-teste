package com.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.restassured.AllureRestAssured;



public class AppTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
        RestAssured.filters(new AllureRestAssured());

    }

    @Test
    @DisplayName("Teste: (GET) Busca Status da Aplicação")

    
public void testeSimplesGet() {
    given()
        .contentType(ContentType.JSON)
        .log().all()
    .when()
        .get("/test")
    .then()
        .log().all()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body("status", equalTo("ok"))
        .body("method", equalTo("GET"));

    System.out.println("Resposta 200: teste executado com sucesso!");
}

}
