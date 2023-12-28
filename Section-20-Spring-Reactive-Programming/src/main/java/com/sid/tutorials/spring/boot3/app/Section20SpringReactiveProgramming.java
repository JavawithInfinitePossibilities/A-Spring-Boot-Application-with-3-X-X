package com.sid.tutorials.spring.boot3.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootApplication
public class Section20SpringReactiveProgramming {
    public static void main(String[] args) {
        SpringApplication.run(Section20SpringReactiveProgramming.class, args);
    }

    /*@Bean
    public ReactiveWebServerFactory reactiveWebServerFactory() {
        return new NettyReactiveWebServerFactory();
    }*/
}
