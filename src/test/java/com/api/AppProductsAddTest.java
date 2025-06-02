package com.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class AppProductsAddTest {
    
 @Test
    @DisplayName("Teste: (POST) Deve criar um novo produto com sucesso")
    public void deveCriarNovoProduto() {
        String requestBody = """
            {
                "title": "Perfume Oil",
                "description": "Mega Discount, Impression of A...",
                "price": 13,
                "discountPercentage": 8.4,
                "rating": 4.26,
                "stock": 65,
                "brand": "Impression of Acqua Di Gio",
                "category": "fragrances",
                "thumbnail": "https://i.dummyjson.com/data/products/11/thumnail.jpg"
            }
        """;

        given()
            .baseUri("https://dummyjson.com")
            .basePath("/products/add")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .filter(new AllureRestAssured())
            .log().all()
        .when()
            .post()
        .then()
            .log().all()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("Perfume Oil"))
            .body("price", equalTo(13))
            .body("brand", equalTo("Impression of Acqua Di Gio"));
    }

}