package com.neznatnov.web.tests;

import com.neznatnov.web.config.TestBase;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class PlusPageTests extends TestBase {

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking the title text on the page")
    @Owner("Veronika Iatckaia")
    public void correctTitleTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });

        step("Verify title text", () -> {
            plusPage.correctTitle();
        });

    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking the button with the sum of the month")
    @Owner("Veronika Iatckaia")
    public void correctMonthlyGetUnsplashButtonTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });

        step("Verify button text", () -> {
            plusPage.correctMonthlyGetUnsplashButton();
        });

    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking the button with the sum of the year")
    @Owner("Veronika Iatckaia")
    public void correctYearlyGetUnsplashButtonTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });

        step("Verify button text", () -> {
            plusPage.correctYearlyGetUnsplashButton();
        });

    }


}
