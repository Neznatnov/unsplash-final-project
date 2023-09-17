package com.neznatnov.api.tests;

import com.neznatnov.api.data.KeyConfig;
import com.neznatnov.api.data.PhotoData;
import com.neznatnov.api.models.ImageModule;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.neznatnov.api.helpers.Endpoints.*;
import static com.neznatnov.api.spec.Specification.requestSpec;
import static com.neznatnov.api.spec.Specification.responseSpec200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Owner("Veronika Iatckaia")
public class PhotoTest {
    private static KeyConfig userConfig = ConfigFactory.create(KeyConfig.class, System.getProperties());

    @Test
    @Tag("unsplash_api")
    @DisplayName("Get a random photos")
    public void testGetRandomPhoto() {
        step("Getting two random photos", () -> {
            List<ImageModule> images = given()
                    .spec(requestSpec)
                    .param("count", 2)
                    .header("Authorization", "Client-ID " + userConfig.apiKey())
                    .when()
                    .get(GET_PHOTOS_RANDOM)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .extract().body().jsonPath().getList("", ImageModule.class);
            step("Checking that the received list contains two photos which belong to two different photo owners", () -> {
            assertThat(images).hasSize(2);
            String ownerId1 = images.get(0).getUser().getId();
            String ownerId2 = images.get(1).getUser().getId();
            assertThat(ownerId1).isNotEqualTo(ownerId2);
            });
        });
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Search photos by keyword")
    public void testSearchPhotos() {
        step("Looking for two photos by keyword office with a special order", () -> {
            List<ImageModule> images = given()
                    .spec(requestSpec)
                    .param("query", PhotoData.PHOTO_KEY_WORD)
                    .param("per_page", 2)
                    .param("order_by", PhotoData.ORDER)
                    .header("Authorization", "Client-ID " + userConfig.apiKey())
                    .when()
                    .get(GET_PHOTO_SEARCH)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .extract().body().jsonPath().getList("results", ImageModule.class);
            step("Checking that the received list contains two photos with the keyword office", () -> {
            assertThat(images).hasSize(2);
            });
        });
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Check retrieving statistics for a specific photo")
    public void testGetPhotoStatistics() {
        step("Getting photo statistics for the last 30 days", () -> {
            given()
                    .spec(requestSpec)
                    .header("Authorization", "Client-ID " + userConfig.apiKey())
                    .pathParam("photoId", PhotoData.PHOTO_ID)
                    .contentType(ContentType.JSON)
                    .param("quantity", 30)
                    .when()
                    .get(GET_PHOTO_STATISTICS)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .body("id", equalTo(PhotoData.PHOTO_ID));
        });
    }
}