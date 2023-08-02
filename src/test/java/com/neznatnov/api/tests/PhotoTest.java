package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import com.neznatnov.api.data.PhotoData;
import com.neznatnov.api.models.SearchResultModel;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.neznatnov.api.helpers.Endpoints.*;
import static com.neznatnov.api.spec.Specification.requestSpec;
import static com.neznatnov.api.spec.Specification.responseSpec200;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;



public class PhotoTest {

    BaseData baseTestData = new BaseData();
    PhotoData photoData = new PhotoData();

    @Test
    @Tag("unsplash_api")
    @DisplayName("Getting a random photo")
    @Owner("Veronika Iatckaia")
    public void testGetRandomPhoto() {

        given()
                .spec(requestSpec)
                .param("count", 1)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .when()
                .get(getPhotosRandom)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .extract().response();
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Verifies searching for photos using a specific keyword ")
    @Owner("Veronika Iatckaia")
    public void testSearchPhotos() {

        ValidatableResponse response = given()
                .spec(requestSpec)
                .param("query", photoData.photoKeyWord)
                .param("per_page", 10)
                .param("order_by", photoData.order)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .when()
                .get(getPhotoSearch)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .body("results[0].id", equalTo(photoData.photoIdToSearch));

    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Verifies retrieving statistics for a specific photo")
    @Owner("Veronika Iatckaia")
    public void testGetPhotoStatistics() {

        given()
                .spec(requestSpec)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .pathParam("photoId", photoData.photoId)
                .contentType(ContentType.JSON)
                .param("quantity", 30)
                .when()
                .get(getPhotoStatistics)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .extract().response();

    }
}