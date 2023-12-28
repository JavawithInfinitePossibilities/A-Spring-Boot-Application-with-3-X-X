package com.sid.tutorials.spring.boot3.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author kunmu On 28-12-2023
 */
@Configuration
public class DataSourceConfigDetails {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource springBatchCoreRepository() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.secondary.datasource")
    public DataSource springBatchDataRepository() {
        return DataSourceBuilder.create().build();
    }

}
