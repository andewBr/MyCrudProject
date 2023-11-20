package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config.properties"
})
public interface MyConfig extends Config {

    @Key("URL")
    String getUrl();

    @Key("USERNAME")
    String getUsername();

    @Key("PASSWORD")
    String getPassword();

}
