package com.deutsche.view;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan("com.deutsche.view")
@PropertySource("classpath:application.properties")
public class ViewConf {
}
