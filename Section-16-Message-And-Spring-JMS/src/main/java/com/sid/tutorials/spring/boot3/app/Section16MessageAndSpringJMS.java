package com.sid.tutorials.spring.boot3.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootApplication
@EnableJms
public class Section16MessageAndSpringJMS {
    public static void main(String[] args) {
        SpringApplication.run(Section16MessageAndSpringJMS.class,args);
    }
}
