package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.neznatnov.api.spec.Specification.requestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest {
    BaseData baseTestData = new BaseData();

    /*@Test
    @Tag("unsplash_api")
    @DisplayName("List a user’s liked photos")
    @Owner("Veronika Iatckaia ")
    void uploadGif() {
        step("Проверка: успешная загрузка гифки с ответом 200", () -> {
            UploadModel upload = new UploadModel();
            upload.setApi_key(baseTestData.api_key);
            upload.setSource_image_url(uploadTestData.imageLink);

            given()
                    .spec(requestSpecUpload)
                    .body(upload)
                    .when()
                    .post(postGifsSearch)
                    .then()
                    .spec(responseSpec200);
        });
    }*/


    @Test
    public void testGetUserLikedPhotos() {
        RestAssured.baseURI = "https://api.unsplash.com";
        String username = "juniperphoton"; // Замените на реальное имя пользователя
        String accessKey = "et8QMszGBfa1Edy2ZgtrY17PEY-t9pk1vSzOj7i5bwg";

        Response response =
                given()
                        .header("Authorization", "Client-ID " + accessKey)
                        .pathParam("username", username)
                        .when()
                        .get("/users/{username}/likes")
                        .then()
                        .statusCode(200)
                        .body("$", hasSize(greaterThan(0))) // Проверка, что список фотографий не пустой
                        .body("[0].id", notNullValue()) // Проверка, что у первой фотографии есть ID
                        .extract()
                        .response();


        // Добавьте другие проверки для полей и значений в ответе API
    }
    @Test
    public void testGetUserCollections() {
        RestAssured.baseURI = "https://api.unsplash.com";
        String username = "juniperphoton"; // Замените на реальное имя пользователя
        String accessKey = "et8QMszGBfa1Edy2ZgtrY17PEY-t9pk1vSzOj7i5bwg";

        Response response = given()
                .header("Authorization", "Client-ID " + accessKey)
                .pathParam("username", username)
                .when()
                .get("/users/{username}/collections")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty())) // Ensure the response is not empty
                .body("[0].id", notNullValue()) // Ensure the first collection has an ID
                .extract().response();

        // You can perform further validations based on the specific requirements of your test
        // For example, you can check the fields and values of each collection in the response.
    }
}
