package com.deutsche.users.repo;

import com.deutsche.users.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
class UserServiceTest {

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    @Transactional
    @Rollback(false)
    public void setUp(){
        User user = User.builder()
                .name("Ivan")
                .login("admin")
                .build();
        userRepo.save(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void getByLogin() {
        Assert.assertEquals("Ivan", userRepo.findByLogin("admin").getName());
    }
}