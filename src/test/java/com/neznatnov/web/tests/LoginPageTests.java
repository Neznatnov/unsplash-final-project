package com.neznatnov.web.tests;

import com.neznatnov.web.config.TestBase;
import com.neznatnov.web.pages.LoginPage;
import com.neznatnov.web.pages.MainPage;
import com.neznatnov.web.data.LoginPageData;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("Veronika Iatckaia")
public class LoginPageTests extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();
    private LoginPageData loginPageData = new LoginPageData();

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Check that all elements are on the page")
    public void correctPageTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Log In button", () -> {
            mainPage.clickLogInButton();
        });
        step("Check that all elements are visible", () -> {
            loginPage.allElementsExistLoginPage();
        });
    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Log in with incorrect credentials")
    public void logInIncorrect() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Log In button", () -> {
            mainPage.clickLogInButton();
        });
        step("Set incorrect email", () -> {
            loginPage.setIncorrectEmail(loginPageData.email);
        });
        step("Set incorrect password", () -> {
            loginPage.setIncorrectPassword(loginPageData.password);
        });
        step("Click on the Log In button", () -> {
            loginPage.clickLoginButton();
        });
        step("Verify failed authorization", () -> {
            loginPage.verifyLoginErrorText(loginPageData.ERROR_LOGIN_TEXT);
        });
    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Log in with correct credentials")
    public void logInCorrect() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Log In button", () -> {
            mainPage.clickLogInButton();
        });
        step("Set correct email", () -> {
            loginPage.setCorrectEmail(userConfig.email());
        });
        step("Set correct password", () -> {
            loginPage.setCorrectPassword(userConfig.password());
        });
        step("Click on the Log In button", () -> {
            loginPage.clickLoginButton();
        });
        step("Verify successful authorization", () -> {
            loginPage.loginPageCheckSuccessfulLogin();
        });
    }
}
