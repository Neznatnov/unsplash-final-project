package com.neznatnov.web.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.neznatnov.web.helpers.Attach;
import com.neznatnov.web.pages.loginPage.LoginPage;
import com.neznatnov.web.pages.mainPage.MainPage;
import com.neznatnov.web.pages.plusPage.PlusPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {
    static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    static WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    public LoginPage loginPage = new LoginPage();
    public MainPage mainPage = new MainPage();
    public PlusPage plusPage = new PlusPage();
    protected String email = userConfig.getEmail();
    protected String password = userConfig.getPassword();


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.remote = config.getRemoteUrl();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        //Configuration.browserBinary = "/Applications/Google Chrome.app";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterEach
    void closeWindow() {
        Selenide.closeWindow();
    }
}
