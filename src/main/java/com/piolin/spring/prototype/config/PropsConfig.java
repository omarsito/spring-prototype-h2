package com.piolin.spring.prototype.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropsConfig {

    private static final Logger LOG = LoggerFactory.getLogger(PropsConfig.class);

    @Autowired
    private Environment env;

    public String getPropsValue(String configKey){
        String envInfo;
        String configValue = null;
        if (env != null) {
            envInfo = "Env: " + env.toString();
            configValue = env.getProperty(configKey);
        }else{
            envInfo = "Env: Properties file not available ...";
        }
        LOG.info(envInfo);
        return configValue;
    }
}