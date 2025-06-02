package com.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class AppProductsTest {
     private String getValidToken() {
        String requestBody = """
            {
                "username": "emilys",
                "password": "emilyspass"
            }
        """;

        Response response = given()
            .baseUri("https://dummyjson.com")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .post("/auth/login")
        .then()
            .log().all()
            .statusCode(200)
            .extract().response();

        String token = response.path("accessToken");
        System.out.println("TOKEN: " + token); // pra debug
        return token;
    }

    @Test
    @DisplayName("Teste: (GET) Deve retornar lista de produtos ao passar token válido")
    public void deveRetornarProdutosComTokenValido() {
        String token = getValidToken();

        given()
            .baseUri("https://dummyjson.com")
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer " + token)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get("/auth/products")
        .then()
            .log().all()
            .statusCode(200)
            .body("products", not(empty()))
            .body("products.size()", greaterThan(0));
    }

     @Test
    @DisplayName("Teste: (GET) Deve Buscar produtos com o token inválido - deve retornar 401")
    public void buscarProdutosComTokenInvalido() {
        given()
            .baseUri("https://dummyjson.com")
            .header("Authorization", "Bearer tokenInvalido123")
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get("/auth/products")
        .then()
            .log().all()
            .statusCode(401)
            .body("message", containsStringIgnoringCase("invalid"));
    }
    // Não deveria retornar 403 nesse caso?
     @Test
    @DisplayName("Teste: (GET) Buscar produtos sem token - deve retornar 401")
    public void buscarProdutosSemToken() {
        given()
            .baseUri("https://dummyjson.com")
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .get("/auth/products")
        .then()
            .log().all()
            .statusCode(401)
            .body("message", containsStringIgnoringCase("required"));
    }
}
