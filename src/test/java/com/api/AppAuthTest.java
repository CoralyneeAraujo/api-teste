package com.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AppAuthTest {

    @Test
    @DisplayName("Teste: (POST) Login deve retornar 200 e token válido")
    public void testeLoginRetornaToken() {
        String requestBody = """
            {
                "username": "emilys",
                "password": "emilyspass"
            }
        """;

        given()
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
            .contentType(ContentType.JSON)
            .body("accessToken", allOf(notNullValue(), not(isEmptyString())))
            .body("refreshToken", allOf(notNullValue(), not(isEmptyString())))
            .body("id", equalTo(1))
            .body("username", equalTo("emilys"))
            .body("email", equalTo("emily.johnson@x.dummyjson.com"))
            .body("firstName", equalTo("Emily"))
            .body("lastName", equalTo("Johnson"))
            .body("gender", equalTo("female"))
            .body("image", startsWith("https://dummyjson.com/icon/emilys"));
    }

    @Test
    @DisplayName("Teste: (POST) Usuário inválido deve retornar erro")
    public void testeUsuarioInvalido() {
        String requestBody = """
            {
                "username": "usuario",
                "password": "emilyspass"
            }
        """;

        given()
            .baseUri("https://dummyjson.com")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .filter(new AllureRestAssured())  
            .log().all()
        .when()
            .post("/auth/login") 
         .then()
            .log().all()
            .statusCode(anyOf(equalTo(400), equalTo(401))) 
            .body("message", containsStringIgnoringCase("invalid"));
    }

    @Test
    @DisplayName("Teste: (POST) senha inválida deve retornar erro")
    public void testeSenhaInvalida() {
        String requestBody = """
            {
                "username": "emilys",
                "password": "senha"
            }
        """;

        given()
            .baseUri("https://dummyjson.com")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .filter(new AllureRestAssured())  
            .log().all()
        .when()
            .post("/auth/login") 
         .then()
            .log().all()
            .statusCode(anyOf(equalTo(400), equalTo(401))) 
            .body("message", containsStringIgnoringCase("invalid"));
    }
}
