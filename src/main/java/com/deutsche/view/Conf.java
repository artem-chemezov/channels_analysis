package com.deutsche.view;

import com.deutsche.view.tools.Keyboard;
import com.deutsche.view.tools.KeyboardRows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.ApiContextInitializer;


@Configuration
@ComponentScan("com.deutsche.view")
@PropertySource("classpath:application.properties")
public class Conf {

}
