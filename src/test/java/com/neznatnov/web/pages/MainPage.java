package com.neznatnov.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.href;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement logoPic = $("a.XDKcL.eziW_ svg.UP8CN[width='32'][height='32'][viewBox='0 0 32 32'][version='1.1']");
    private final SelenideElement searchInput = $("input[type='search");
    private final SelenideElement exploreButton = $("a.Ue8P3.KHq0c.BWSMq");
    private final SelenideElement advertiseButton = $("div.cPCmf a.vZKGD.O1X3N.AYTqW.Ue8P3.KHq0c");
    private final SelenideElement plusButton = $("div.cPCmf a[href='/plus']");
    private final SelenideElement loginButton = $("a.cLLOA.p1cWU.jpBZ0.EzsBC.KHq0c.XHI2L");
    private final SelenideElement submitAPhotoButton = $("button[data-test='SecondaryMenu-Trigger']");
    private final SelenideElement menuButton = $(".QeEH8");
    public final SelenideElement twitter = $("[title='Follow Unsplash on Twitter']");
    private final SelenideElement facebook = $("[title='Follow Unsplash on Facebook']");
    private final SelenideElement instagram = $("[title='Follow Unsplash on Instagram']");


    public MainPage allElementsExistHeaderMainPage() {
        logoPic.shouldBe(Condition.visible);
        searchInput.shouldBe(Condition.visible);
        exploreButton.shouldBe(Condition.visible);
        advertiseButton.shouldBe(Condition.visible);
        plusButton.shouldBe(Condition.visible);
        loginButton.shouldBe(Condition.visible);
        submitAPhotoButton.shouldBe(Condition.visible);
        menuButton.shouldBe(Condition.visible);

        return this;
    }

    public MainPage clickPlusButton() {
        plusButton.click();

        return this;
    }

    public MainPage clickMenuButton() {
        menuButton.click();

        return this;
    }

    public MainPage clickLogInButton() {
        loginButton.click();

        return this;
    }

    public MainPage correctTwitterLink(String link) {
        twitter.shouldHave(href(link));



        return this;
    }

    public MainPage correctFacebookLink(String link) {
        facebook.shouldHave(href(link));

        return this;
    }

    public MainPage correctInstagramLink(String link) {
        instagram.shouldHave(href(link));

        return this;
    }
}
