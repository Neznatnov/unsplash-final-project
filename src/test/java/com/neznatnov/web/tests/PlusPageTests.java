package com.neznatnov.web.tests;

import com.neznatnov.web.config.TestBase;
import com.neznatnov.web.pages.MainPage;
import com.neznatnov.web.pages.PlusPage;
import com.neznatnov.web.data.PlusPageData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
@Owner("Veronika Iatckaia")
public class PlusPageTests extends TestBase {
    private PlusPage plusPage = new PlusPage();
    private MainPage mainPage = new MainPage();
    private PlusPageData plusPageData = new PlusPageData();

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check the title text on the page")
    public void correctTitleTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });
        step("Verify title text", () -> {
            plusPage.correctTitle(plusPageData.TITLE);
        });
    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check the button with the monthly price")
    public void correctMonthlyGetUnsplashButtonTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });
        step("Verify button text", () -> {
            plusPage.correctMonthlyGetUnsplashButton(plusPageData.MONTHLY_TEXT);
        });
    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check the button with the yearly price")
    public void correctYearlyGetUnsplashButtonTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Plus Button", () -> {
            mainPage.clickPlusButton();
        });
        step("Verify button text", () -> {
            plusPage.correctYearlyGetUnsplashButton(plusPageData.YEARLY_TEXT);
        });
    }
}
