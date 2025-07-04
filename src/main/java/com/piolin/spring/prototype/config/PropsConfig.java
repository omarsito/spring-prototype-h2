package com.piolin.spring.prototype.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropsConfig {

    @Autowired
    private Environment env;

    public String getPropsValue(String configKey){
        return env.getProperty(configKey);
    }
}