package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PhotoTest {
    BaseData baseTestData = new BaseData();
    public String accessKey = "et8QMszGBfa1Edy2ZgtrY17PEY-t9pk1vSzOj7i5bwg";
    public String photoId = "1khpK68QqBY"; // ID фотографии, которую вы хотите лайкнуть
    public String userId = "flN3pLjhDLw";

    @Test
    public void testGetRandomPhoto() {
        // Установите базовый URL для API Unsplash
        RestAssured.baseURI = "https://api.unsplash.com";


        // Выполните GET-запрос на эндпоинт /photos/random
        Response response = given()
                .param("count", 1) // Возвращаем только одну случайную фотографию
                .header("Authorization", "Client-ID " + accessKey)
                .when()
                .get("/photos/random")
                .then()
                .statusCode(200)
                .contentType("application/json")
                //.body("id", hasKey("raw"))
                .extract().response();

        // Выведите в консоль JSON-ответ
        System.out.println(response.asPrettyString());
    }

    @Test
    public void testSearchPhotos() {
        String photoIdToSearch = "VWcPlbHglYc"; // ID фотографии, которую нужно найти

        RestAssured.baseURI = "https://api.unsplash.com";

        given()
                .param("query", "office") // Поиск фотографий по запросу "office"
                .param("per_page", 10) // Количество фотографий на странице
                .param("order_by", "relevant") // Сортировка по релевантности
                .header("Authorization", "Client-ID " + accessKey)
                .when()
                .get("/search/photos")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("results[0].id", equalTo(photoIdToSearch));// Проверка, что искомый ID
    }

    @Test
    public void testGetPhotoStatistics() {

        String photoId = "LF8gK8-HGSg";

        RestAssured.baseURI = "https://api.unsplash.com";

        Response response = given()
                .header("Authorization", "Client-ID " + accessKey)
                .contentType(ContentType.JSON)
                .param("resolution", "days")
                .param("quantity", 30)
                .when()
                .get("/photos/" + photoId + "/statistics")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        // Validating total number of downloads, views, and likes
        int totalDownloads = response.path("downloads.total");
        int totalViews = response.path("views.total");
        int totalLikes = response.path("likes.total");

        // Validating the historical data for downloads, views, and likes
        List<Map<String, Object>> downloadValues = response.path("downloads.historical.values");
        for (Map<String, Object> value : downloadValues) {
            int downloadValue = Integer.parseInt(value.get("value").toString());
            // Add assertions based on the historical data, if needed
            // For example, to ensure that each download value is greater than or equal to 0:
            // assertThat(downloadValue, greaterThanOrEqualTo(0));
        }

        List<Map<String, Object>> viewValues = response.path("views.historical.values");
        for (Map<String, Object> value : viewValues) {
            int viewValue = Integer.parseInt(value.get("value").toString());
            // Add assertions based on the historical data, if needed
            // For example, to ensure that each view value is greater than or equal to 0:
            // assertThat(viewValue, greaterThanOrEqualTo(0));
        }

        List<Map<String, Object>> likeValues = response.path("likes.historical.values");
        for (Map<String, Object> value : likeValues) {
            int likeValue = Integer.parseInt(value.get("value").toString());
            // Add assertions based on the historical data, if needed
            // For example, to ensure that each like value is greater than or equal to 0:
            // assertThat(likeValue, greaterThanOrEqualTo(0));
        }


    }
}