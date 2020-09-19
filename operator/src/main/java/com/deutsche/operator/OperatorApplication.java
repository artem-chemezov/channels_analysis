package com.deutsche.operator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OperatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperatorConf.class, args);
    }

}
