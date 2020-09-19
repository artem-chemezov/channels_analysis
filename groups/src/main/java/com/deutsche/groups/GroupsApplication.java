package com.deutsche.groups;

import com.deutsche.groups.rabbit.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(RabbitConfiguration.class)
public class GroupsApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GroupConf.class, args);

//        GroupServiceImpl getter = context.getBean(GroupServiceImpl.class);
//        var res = getter.getPosts("tproger", 3);
//        System.out.println(res);
    }

}
