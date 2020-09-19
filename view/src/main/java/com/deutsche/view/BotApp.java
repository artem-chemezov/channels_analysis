package com.deutsche.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableEurekaClient
public class BotApp {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        ConfigurableApplicationContext context = SpringApplication.run(ViewConf.class, args);
    }
}
