package com.deutsche.groups;

import com.deutsche.groups.rabbit.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(RabbitConfiguration.class)
@EnableEurekaClient
public class GroupsApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GroupConf.class, args);
    }

}
