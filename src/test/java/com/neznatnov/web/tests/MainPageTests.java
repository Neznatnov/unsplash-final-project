package com.neznatnov.web.tests;

import com.neznatnov.web.config.TestBase;
import com.neznatnov.web.pages.MainPage;
import com.neznatnov.web.data.MainPageData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
@Owner("Veronika Iatckaia")
public class MainPageTests extends TestBase {
    private MainPage mainPage = new MainPage();
    private MainPageData mainPageData = new MainPageData();
    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check that all elements are in the header")
    public void correctHeaderTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Check that all elements are visible", () -> {
            mainPage.allElementsExistHeaderMainPage();
        });
    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check correct social network links in the menu tab")
    public void correctSocialMediaLinksTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Menu button", () -> {
            mainPage.clickMenuButton();
        });
        step("Check Twitter link", () -> {
            mainPage.correctTwitterLink(mainPageData.TWITTER);
        });
        step("Check Facebook link", () -> {
            mainPage.correctFacebookLink(mainPageData.FACEBOOK);
        });
        step("Check Instagram link", () -> {
            mainPage.correctInstagramLink(mainPageData.INSTAGRAM);
        });
    }
}
