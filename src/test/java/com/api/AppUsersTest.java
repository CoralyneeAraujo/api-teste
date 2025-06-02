package com.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.restassured.AllureRestAssured;


public class AppUsersTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
        RestAssured.filters(new AllureRestAssured());

    }

    @Test
    @DisplayName("Teste: (GET) Buscar usuários com dados válidos")

    public void testeGetUsersSuccess() {
        System.out.println("Executando teste com dados válidos...");
        
        given()
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .get("/users")
        .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("users", not(empty()))
            .body("users[0].id", equalTo(1))
            .body("users[0].firstName", equalTo("Emily"))  
            .body("users[1].id", equalTo(2))
            .body("users[1].firstName", equalTo("Michael"));
        
        System.out.println("Resposta 200: Lista de usuários recuperada com sucesso!");
    }
    @Test

    @DisplayName("Teste: (GET) Buscar todos os usuários")

    public void testeGetUsersFail() {
        System.out.println("Executando teste com dados inválidos...");
        
        given()
            .contentType(ContentType.JSON)
            .log().all()
        .when()
            .get("/users")
        .then()
            .log().all()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("users", not(empty()));
         

    }
    
    
}
