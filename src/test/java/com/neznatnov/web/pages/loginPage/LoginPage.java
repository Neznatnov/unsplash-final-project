package com.neznatnov.web.pages.loginPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement logoPic = $(".login__logo");
    private final SelenideElement loginTitle = $(".login__title");
    private final SelenideElement loginSubTitle = $(".login__subtitle");
    private final SelenideElement emailInput = $("input.form-control[type='email']");
    private final SelenideElement passwordInput = $("input.form-control[type='password']");
    private final SelenideElement loginButton = $("input.btn[type='submit']");
    private final SelenideElement joinUsSection = $("a[href='/join']");
    private final SelenideElement errorBanner = $(".flash__message");
    private final SelenideElement personalAccountPic = $("img.D1hjc.ihzXr[loading='lazy'][src='https://images.unsplash.com/placeholder-avatars/extra-large.jpg?auto=format&fit=crop&w=32&h=32&q=60&crop=faces&bg=fff'][width='32'][height='32']");

    public LoginPage allElementsExistLoginPage() {
        logoPic.shouldBe(Condition.visible);
        loginTitle.shouldBe(Condition.visible);
        loginSubTitle.shouldBe(Condition.visible);
        emailInput.shouldBe(Condition.visible);
        passwordInput.shouldBe(Condition.visible);
        loginButton.shouldBe(Condition.visible);
        joinUsSection.shouldBe(Condition.visible);

        return this;
    }

    public LoginPage setIncorrectEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    public LoginPage setIncorrectPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }


    public LoginPage clickLoginButton() {
        loginButton.click();

        return this;
    }

    public LoginPage verifyLoginErrorText(String errorLoginText) {
        errorBanner.shouldHave(text(errorLoginText));

        return this;
    }

    public LoginPage setCorrectEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public LoginPage setCorrectPassword(String password) {
        passwordInput.setValue(password);

        return this;
    }

    public LoginPage loginPageCheckSuccessfulLogin() {
        personalAccountPic.shouldBe(visible);

        return this;
    }
}
