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

public class LoginPageTests extends TestBase {
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Checking that all elements are on the page")
    @Owner("Veronika Iatckaia")
    public void correctPageTest() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Log In button", () -> {
            mainPage.clickLogInButton();
        });
        step("Check that all elements are on the page", () -> {
            loginPage.allElementsExistLoginPage();
        });

    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Log in with incorrect credentials")
    @Owner("Veronika Iatckaia")
    public void logInIncorrect() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("нажать на лог ин", () -> {
            mainPage.clickLogInButton();
        });
        step("Click on the Log In button", () -> {
            loginPage.clickLoginButton();
        });
        step("Enter incorrect email", () -> {
            loginPage.setIncorrectEmail();
        });
        step("Enter incorrect password", () -> {
            loginPage.setIncorrectPassword();
        });
        step("Click on the Log In button", () -> {
            loginPage.clickLoginButton();
        });
        step("Verify failed authorization", () -> {
            loginPage.verifyLoginErrorText();
        });


    }

    @Test
    @Tag("unsplash_ui")
    @DisplayName("Log in with correct credentials")
    @Owner("Veronika Iatckaia")
    public void logInCorrect() {
        step("Open website Unsplash", () -> open(baseUrl));

        step("Click on the Log In button", () -> {
            mainPage.clickLogInButton();
        });
        step("Enter correct email", () -> {
            loginPage.setCorrectEmail(email);
        });
        step("Enter correct password", () -> {
            loginPage.setCorrectPassword(password);
        });
        step("Click on the Log In button", () -> {
            loginPage.clickLoginButton();
        });
        step("Verify successful authorization", () -> {
            loginPage.loginPageCheckSuccessfulLogin();
        });

    }
}
