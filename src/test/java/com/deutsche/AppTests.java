package com.deutsche;

import com.deutsche.view.Conf;
import com.deutsche.view.handlers.ResponseHandler;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;

public class AppTests {
    @Autowired
    ApplicationContext context;

    @Test
    @Before
    public void contextLoad(){
        ApiContextInitializer.init();
    }
}
