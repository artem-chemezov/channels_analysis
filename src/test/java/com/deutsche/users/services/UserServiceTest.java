package com.deutsche.users.services;

import com.deutsche.users.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@ComponentScan(basePackages={"com.deutsche"})
@RunWith(SpringRunner.class)
class UserServiceTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Test
    public void testUserServiceOnCorrectResponce(){
        userService.saveUser(User.builder().name("name").login("login").build());
        Assert.assertEquals("name", userService.getByLogin("login").getName());
    }
}