package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import com.neznatnov.api.data.CollectionsData;
import com.neznatnov.api.models.SearchResultModel;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
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
import static org.hamcrest.Matchers.*;

public class CollectionsTest {
    BaseData baseTestData = new BaseData();
    CollectionsData collectionsData = new CollectionsData();

    @Test
    @Tag("unsplash_api")
    @DisplayName("Getting a collection by correct id")
    @Owner("Veronika Iatckaia")
    public void testGetCollectionById() {
        step("When adding the correct ID to the endpoint, the collection corresponding to it comes in response", () -> {
        given()
                .spec(requestSpec)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .pathParam("id", collectionsData.collectionId)
                .when()
                .get(getCollectionsId)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(collectionsData.collectionId))
                .body("total_photos", greaterThanOrEqualTo(0))
                .extract().response();
        });
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Checking a selection of photos in a collection")
    @Owner("Veronika Iatckaia")
    public void testGetCollectionPhotos() {
        step("", () -> {
        List<SearchResultModel> results = given()
                .spec(requestSpec)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .pathParam("id",collectionsData.collectionId)
                .param("per_page", 5)
                .when()
                .get(getCollectionsPhotos)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .extract().body().jsonPath().getList("", SearchResultModel.class);
        assertThat(results.size()).isEqualTo(5);
        });
    }
}
