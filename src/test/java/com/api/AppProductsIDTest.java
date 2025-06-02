package com.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AppProductsIDTest {
    
   @Test
    @DisplayName("Teste: (GET) Deve retornar um produto válido por Identificador")
    public void deveRetornarProdutoPorId() {
        given()
            .baseUri("https://dummyjson.com")
            .basePath("/products/1")
            .accept(ContentType.JSON)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get()
        .then()
            .log().all()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", notNullValue());
    }

    @Test
    @DisplayName("Teste: (GET) Deve retornar 404 por Identificador não encontrado")
    public void deveRetornar404ParaProdutoInexistente() {
        given()
            .baseUri("https://dummyjson.com")
            .basePath("/products/0")
            .accept(ContentType.JSON)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get()
        .then()
            .log().all()
            .statusCode(404)
            .body("message", equalTo("Product with id '0' not found"));
    }
}