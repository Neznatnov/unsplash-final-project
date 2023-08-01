package com.neznatnov.api.tests;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.hamcrest.Matchers.*;

import com.neznatnov.api.data.BaseData;

public class CollectionsTest {
    BaseData baseTestData = new BaseData();
    public String accessKey = "et8QMszGBfa1Edy2ZgtrY17PEY-t9pk1vSzOj7i5bwg";
    @Test
    public void testGetCollectionById() {
        String collectionId = "90Cf9k_JhcQ"; // Replace with the real collection ID

        RestAssured.baseURI = "https://api.unsplash.com";

        Response response = given()
                .header("Authorization", "Client-ID " + accessKey)
                .pathParam("id", collectionId)
                .when()
                .get("/collections/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(collectionId)) // Ensure the collection ID matches
                .body("total_photos", greaterThanOrEqualTo(0)) // Ensure total_photos is non-negative
                .body("links.self", equalTo("https://api.unsplash.com/collections/" + collectionId)) // Ensure the self link matches
                .extract().response();

        // You can perform further validations based on the specific requirements of your test
        // For example, you can check additional fields and values in the collection response.
    }

    @Test
    public void testGetCollectionPhotos() {
        String collectionId = "90Cf9k_JhcQ"; // Replace with the real collection ID

        RestAssured.baseURI = "https://api.unsplash.com";

        Response response = given()
                .header("Authorization", "Client-ID " + accessKey)
                .pathParam("id", collectionId)
                .param("per_page", 10) // Optional: You can specify the number of photos per page
                .when()
                .get("/collections/{id}/photos")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThan(0))) // Ensure the response contains at least one photo
                .body("[0].id", notNullValue()) // Ensure the first photo has an ID
                .extract().response();

        // You can perform further validations based on the specific requirements of your test
        // For example, you can check additional fields and values in the photos response.
    }
}
