package com.neznatnov.web.config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/web-tests/${env}.properties"
})
public interface WebDriverConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://unsplash.com/")
    String getBaseUrl();

    @Key("browser")
    @DefaultValue("CHROME")
    String getBrowser();

    @Key("size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("version")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("pageLoadStrategy")
    String getPageLoadStrategy();
}
