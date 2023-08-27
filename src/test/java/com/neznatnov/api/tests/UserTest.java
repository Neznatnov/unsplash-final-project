package com.neznatnov.api.tests;

import com.neznatnov.api.data.KeyConfig;
import com.neznatnov.api.data.UserData;
import com.neznatnov.api.models.CollectionModule;
import com.neznatnov.api.models.ImageModule;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.neznatnov.api.helpers.Endpoints.GET_USER_COLLECTIONS;
import static com.neznatnov.api.helpers.Endpoints.GET_USER_LIKES;
import static com.neznatnov.api.spec.Specification.requestSpec;
import static com.neznatnov.api.spec.Specification.responseSpec200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
@Owner("Veronika Iatckaia")
public class UserTest {
    private static KeyConfig userConfig = ConfigFactory.create(KeyConfig.class, System.getProperties());


    @Test
    @Tag("unsplash_api")
    @DisplayName("Get a list of photos that a particular user has liked")
    public void testGetUserLikedPhotos() {
        step("Checking the list of photos that the user has liked and that the list contains at least one photo with a non-empty id", () -> {
            List<ImageModule> images = given()
                    .spec(requestSpec)
                    .header("Authorization", "Client-ID " + userConfig.apiKey())
                    .pathParam("username", UserData.USERNAME)
                    .param("per_page", 2)
                    .when()
                    .get(GET_USER_LIKES)
                    .then()
                    .spec(responseSpec200)
                    .body("$", hasSize(greaterThan(0)))
                    .body("[0].id", notNullValue())
                    .extract().body().jsonPath().getList("", ImageModule.class);
            step("Checking that each of the two photos has a positive number of likes", () -> {
            assertThat(images).hasSize(2);
            assertThat(images.get(0).getLikes()).isPositive();
            assertThat(images.get(1).getLikes()).isPositive();
            });
        });
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Get collections created by a specific user")
    public void testGetUserCollections() {
        step("For a specific username, we get the two collections that this user created", () -> {
            List<CollectionModule> usersCollections = given()
                    .spec(requestSpec)
                    .header("Authorization", "Client-ID " + userConfig.apiKey())
                    .pathParam("username", UserData.USERNAME)
                    .param("per_page", 2)
                    .when()
                    .get(GET_USER_COLLECTIONS)
                    .then()
                    .spec(responseSpec200)
                    .contentType(ContentType.JSON)
                    .extract().body().jsonPath().getList("", CollectionModule.class);
            step("Checking that both collections have the same username as the username that created the collection", () -> {
            assertThat(usersCollections).hasSize(2);
            assertThat(usersCollections.get(0).getUser().getUsername()).isEqualTo(UserData.USERNAME);
            assertThat(usersCollections.get(1).getUser().getUsername()).isEqualTo(UserData.USERNAME);
            });
        });
    }
}
