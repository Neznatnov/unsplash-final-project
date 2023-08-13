package com.neznatnov.web.tests;

import com.neznatnov.web.config.TestBase;
import com.neznatnov.web.pages.loginPage.LoginPage;
import com.neznatnov.web.pages.mainPage.MainPage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPageTests extends TestBase {
    private MainPage mainPage = new MainPage();
    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking that all elements are in the header")
    @Owner("Veronika Iatckaia")
    public void correctHeaderTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Check that all elements are in the header", () -> {
            mainPage.allElementsExistHeaderMainPage();
        });

    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking social network links in the menu tab")
    @Owner("Veronika Iatckaia")
    public void correctSocialMediaLinksTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Menu button", () -> {
            mainPage.clickMenuButton();
        });
        step("Check Twitter link", () -> {
            mainPage.correctTwitterLink();
        });
        step("Check Facebook link", () -> {
            mainPage.correctFacebookLink();
        });
        step("Check Instagram link", () -> {
            mainPage.correctInstagramLink();
        });

    }


}
