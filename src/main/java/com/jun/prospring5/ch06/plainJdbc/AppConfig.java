package com.jun.prospring5.ch06.plainJdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/jdbc2.properties")
@ComponentScan(basePackages = "com.jun.prospring5.ch06.plainJdbc")
public class AppConfig {
    private Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${dbpassword}")
    private String password;
    @Value("${dbusername}")
    private String username;
    @Value("${url}")
    private String url;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        return dataSource;
    }
}
