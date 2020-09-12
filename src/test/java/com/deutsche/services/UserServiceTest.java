package com.deutsche.services;

import com.deutsche.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

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