package com.deutsche.operator;

import com.deutsche.operator.rabbit.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@Import(RabbitConfiguration.class)
public class OperatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperatorConf.class, args);
    }

}
