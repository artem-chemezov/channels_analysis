package com.deutsche.groups;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GroupsApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GroupConf.class, args);

//        GroupServiceImpl getter = context.getBean(GroupServiceImpl.class);
//        var res = getter.getPosts("tproger", 3);
//        System.out.println(res);
    }

}
