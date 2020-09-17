package com.deutsche;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
