package com.neznatnov.web.config;


import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/web-tests/user.properties"
})
public interface UserConfig extends Config {

    @Key("email")
    String getEmail();

    @Key("password")
    String getPassword();
}