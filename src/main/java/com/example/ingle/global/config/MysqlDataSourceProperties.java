package com.example.ingle.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class MysqlDataSourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
