package com.piolin.spring.prototype.config;

import com.piolin.spring.prototype.business.CustomerBusiness;
import com.piolin.spring.prototype.database.dao.CustomerDao;
import com.piolin.spring.prototype.util.Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.piolin.spring.prototype")
public class AppConfig {

    @Bean(name = "util")
    public Util util(){
        return new Util();
    }

    @Bean(name = "clientBusiness")
    public CustomerBusiness clientBusiness(){
        return new CustomerBusiness();
    }

    @Bean(name = "clientDao")
    public CustomerDao clientDao(){
        return new CustomerDao();
    }

}