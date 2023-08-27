package com.neznatnov.api.data;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/api-tests/key.properties"
})
public interface KeyConfig extends Config {
    @Key("apiKey")
    String apiKey();
}