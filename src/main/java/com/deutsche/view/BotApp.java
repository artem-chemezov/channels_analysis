package com.deutsche.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class BotApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Conf.class, args);
    }
}
