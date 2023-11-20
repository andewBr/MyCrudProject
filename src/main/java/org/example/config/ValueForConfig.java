package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;

@Getter
@AllArgsConstructor
public enum ValueForConfig {

    URL(ConfigFactory.create(MyConfig.class, System.getProperties()).getUrl()),
    USERNAME(ConfigFactory.create(MyConfig.class, System.getProperties()).getUsername()),
    PASSWORD(ConfigFactory.create(MyConfig.class, System.getProperties()).getPassword());
    private final String value;
}
