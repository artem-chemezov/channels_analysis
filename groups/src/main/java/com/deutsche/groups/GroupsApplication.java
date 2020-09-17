package com.deutsche.groups;

import com.deutsche.groups.services.GroupServicePostsGetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GroupsApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GroupConf.class, args);

        GroupServicePostsGetter getter = context.getBean(GroupServicePostsGetter.class);
        var res = getter.getPosts("tproger", 3);
        System.out.println(res.size());
    }

}
