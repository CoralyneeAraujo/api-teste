package com.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AppProductsGetTest {
    
  @Test
    @DisplayName("Teste: (GET) Deve retornar todos os produtos")
    public void deveRetornarTodosOsProdutos() {
        given()
            .baseUri("https://dummyjson.com")
            .basePath("/products")
            .accept(ContentType.JSON)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get()
        .then()
            .log().all()
            .statusCode(200)
            .body("products", not(empty()))
            .body("total", greaterThan(0))
            .body("limit", greaterThan(0));
    }
}
