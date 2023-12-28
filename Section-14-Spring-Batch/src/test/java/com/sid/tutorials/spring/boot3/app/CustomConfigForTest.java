package com.sid.tutorials.spring.boot3.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;

/**
 * @author kunmu On 28-12-2023
 */
@TestConfiguration
public class CustomConfigForTest {

    @Autowired
    private DataSource dataSource;

    @Bean
    @Autowired
    public JdbcTransactionManager batchTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean("primaryJobLauncherTestUtils")
    public JobLauncherTestUtils getJobLauncherTestUtils() {
        return new JobLauncherTestUtils() {
            @Autowired
            @Override
            public void setJob(@Qualifier("mySpringBatchFirstJob") Job job) {
                super.setJob(job);
            }
        };
    }
}
