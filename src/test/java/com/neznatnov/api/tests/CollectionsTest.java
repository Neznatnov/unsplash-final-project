package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import com.neznatnov.api.data.CollectionsData;
import com.neznatnov.api.models.CollectionModule;
import com.neznatnov.api.models.ImageModule;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.neznatnov.api.helpers.Endpoints.GET_COLLECTIONS_ID;
import static com.neznatnov.api.helpers.Endpoints.GET_COLLECTIONS_PHOTOS;
import static com.neznatnov.api.spec.Specification.requestSpec;
import static com.neznatnov.api.spec.Specification.responseSpec200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsTest {

    @Test
    @Tag("unsplash_api")
    @DisplayName("Getting a collection by correct id")
    @Owner("Veronika Iatckaia")
    public void testGetCollectionById() {
        step("When adding the correct ID to the endpoint, the collection corresponding to it comes in response", () -> {
            CollectionModule result = given()
                    .spec(requestSpec)
                    .header("Authorization", "Client-ID " + BaseData.API_KEY)
                    .pathParam("id", CollectionsData.COLLECTION_ID)
                    .when()
                    .get(GET_COLLECTIONS_ID)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .extract().body().jsonPath().getObject("", CollectionModule.class);
            step("Checking that the collection id matches and belongs to a specific user", () -> {
            assertThat(result.getId()).isEqualTo(CollectionsData.COLLECTION_ID);
            assertThat(result.getUser().getId()).isEqualTo(CollectionsData.OWNER_ID);
            });
        });
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Checking a selection of photos in a collection")
    @Owner("Veronika Iatckaia")
    public void testGetCollectionPhotos() {
        step("Checking that in a particular collection we are shown the five photos", () -> {
            List<ImageModule> results = given()
                    .spec(requestSpec)
                    .header("Authorization", "Client-ID " + BaseData.API_KEY)
                    .pathParam("id", CollectionsData.COLLECTION_ID)
                    .param("per_page", 5)
                    .when()
                    .get(GET_COLLECTIONS_PHOTOS)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .extract().body().jsonPath().getList("", ImageModule.class);
            assertThat(results).hasSize(5);
            step("Checking that the number of photos matches the requested one and belongs to a specific user", () -> {
            String ownerId = results.get(0).getUser().getId();
            assertThat(ownerId).isEqualTo(CollectionsData.OWNER_ID);
            });
        });
    }
}
