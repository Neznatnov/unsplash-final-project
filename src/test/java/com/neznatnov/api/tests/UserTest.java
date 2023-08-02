package com.neznatnov.api.tests;

import com.neznatnov.api.data.BaseData;
import com.neznatnov.api.data.UserData;
import io.qameta.allure.Owner;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.neznatnov.api.helpers.Endpoints.getUserCollections;
import static com.neznatnov.api.helpers.Endpoints.getUserLikes;
import static com.neznatnov.api.spec.Specification.requestSpec;
import static com.neznatnov.api.spec.Specification.responseSpec200;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest {
    BaseData baseTestData = new BaseData();
    UserData userData = new UserData();


    @Test
    @Tag("unsplash_api")
    @DisplayName("Verifies retrieving liked photos of a specific user")
    @Owner("Veronika Iatckaia")
    public void testGetUserLikedPhotos() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .pathParam("username", userData.username)
                .when()
                .get(getUserLikes)
                .then()
                .spec(responseSpec200)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].id", notNullValue())
                .extract()
                .response();
    }

    @Test
    @Tag("unsplash_api")
    @DisplayName("Getting collections created by a specific user")
    @Owner("Veronika Iatckaia")
    public void testGetUserCollections() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Client-ID " + baseTestData.api_key)
                .pathParam("username", userData.username)
                .when()
                .get(getUserCollections)
                .then()
                .spec(responseSpec200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("[0].id", notNullValue())
                .extract().response();

    }
}
