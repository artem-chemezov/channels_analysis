package com.deutsche.view;

import com.deutsche.view.tools.Keyboard;
import com.deutsche.view.tools.KeyboardRows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;

@Configuration
@ComponentScan
public class Conf {
    @Bean
    public Bot bot(){
        ApiContextInitializer.init();
        return new Bot();
    }
}
