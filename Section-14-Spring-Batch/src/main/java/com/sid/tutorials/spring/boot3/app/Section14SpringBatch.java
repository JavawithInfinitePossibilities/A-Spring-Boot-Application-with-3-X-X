package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "springBatchCoreRepository", transactionManagerRef = "platformTransactionManager")
public class Section14SpringBatch {

    @Autowired
    private ItemReader<String> itemReader;

    @Autowired
    private ItemProcessor<String, String> itemProcessor;

    @Autowired
    private ItemWriter<String> itemWriter;

    @Autowired
    private MyJobListener myJobListener;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    @Qualifier("platformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Step mySpringBatchFirstStep() {
        return new StepBuilder("mySpringBatchFirstStep", jobRepository)
                .<String, String>chunk(2, platformTransactionManager)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    public Step mySpringBatchSecondStep() {
        return new StepBuilder("mySpringBatchSecondStep", jobRepository)
                .tasklet((StepContribution stepContribution, ChunkContext chunkContext) -> {
                    System.out.println("This is second step !!!");
                    return RepeatStatus.FINISHED;
                }, platformTransactionManager).build();
    }

    @Bean
    public Job mySpringBatchFirstJob() {
        return new JobBuilder("mySpringBatchFirstJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(myJobListener)
                .start(mySpringBatchFirstStep()).on("COMPLETED").to(mySpringBatchSecondStep())
                .end()
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Section14SpringBatch.class, args);
    }
}
