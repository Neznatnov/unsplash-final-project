package com.neznatnov.web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PlusPage {
    private final SelenideElement mainHeadLine = $("h1.i1Flo");
    private final SelenideElement monthlyButton = $(".BOAKz.FCl75.mdMIn.fhQOu");
    private final SelenideElement yearlyButton = $(".BOAKz.FCl75.mdMIn.YYBOE");
    private final SelenideElement getUnsplashButton = $("a.a1ISs.DQBsa.p1cWU.jpBZ0.AYOsT.a2bYd.I0aPD.dEcXu.hoLoT");


    public PlusPage correctTitle(String title) {
        mainHeadLine.shouldHave(Condition.text(title));

        return this;
    }

    public PlusPage correctMonthlyGetUnsplashButton(String monthlyText) {
        monthlyButton.click();
        getUnsplashButton.shouldHave(Condition.exactText(monthlyText));

        return this;
    }

    public PlusPage correctYearlyGetUnsplashButton(String yearlyText) {
        yearlyButton.click();
        getUnsplashButton.shouldHave(Condition.exactText(yearlyText));

        return this;
    }
}
