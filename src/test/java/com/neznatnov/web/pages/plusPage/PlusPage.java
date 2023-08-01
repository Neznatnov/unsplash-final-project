package com.neznatnov.web.pages.plusPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PlusPage {
    private static final String TITLE = "Upgrade to Unsplash+ and start creating with exclusive, royalty-free images.";
    private static final String MONTHLY_TEXT = "Get Unsplash+ for €10/month";
    private static final String YEARLY_TEXT = "Get Unsplash+ for €71/year*";


    private final SelenideElement mainHeadLine = $("h1.i1Flo");
    private final SelenideElement monthlyButton = $(".BOAKz.FCl75.mdMIn.fhQOu");
    private final SelenideElement yearlyButton = $(".BOAKz.FCl75.mdMIn.YYBOE");
    private final SelenideElement getUnsplashButton = $("a.a1ISs.DQBsa.p1cWU.jpBZ0.AYOsT.a2bYd.I0aPD.dEcXu.hoLoT");


    public PlusPage correctTitle() {
        mainHeadLine.shouldHave(Condition.text(TITLE));

        return this;
    }

    public PlusPage correctMonthlyGetUnsplashButton() {
        monthlyButton.click();
        getUnsplashButton.shouldHave(Condition.exactText(MONTHLY_TEXT));

        return this;
    }

    public PlusPage correctYearlyGetUnsplashButton() {
        yearlyButton.click();
        getUnsplashButton.shouldHave(Condition.exactText(YEARLY_TEXT));

        return this;
    }


}
