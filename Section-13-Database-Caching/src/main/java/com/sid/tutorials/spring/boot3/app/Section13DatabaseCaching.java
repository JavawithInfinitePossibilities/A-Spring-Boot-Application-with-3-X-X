package com.sid.tutorials.spring.boot3.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author kunmu On 27-12-2023
 */
@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class Section13DatabaseCaching {
    public static void main(String[] args) {
        SpringApplication.run(Section13DatabaseCaching.class,args);
    }
}
