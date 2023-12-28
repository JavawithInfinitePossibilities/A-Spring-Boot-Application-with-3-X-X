package com.sid.tutorials.spring.boot3.app.config;

import com.sid.tutorials.spring.boot3.app.itemreader.ItemReaderCustom;
import com.sid.tutorials.spring.boot3.app.processer.ItemProcesserCustom;
import com.sid.tutorials.spring.boot3.app.writer.ItemWriterCustom;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;

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

    @Bean
    public JdbcTransactionManager platformTransactionManager() {
        return new JdbcTransactionManager(springBatchCoreRepository());
    }

    @Bean
    public ItemReader<String> itemReader() {
        return new ItemReaderCustom();
    }

    @Bean
    public ItemProcessor<String,String> itemProcessor(){
        return new ItemProcesserCustom();
    }

    @Bean
    public ItemWriter<String> itemWriter(){
        return new ItemWriterCustom();
    }
}
