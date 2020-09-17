package com.deutsche.groups;

import com.deutsche.groups.services.GroupTestConf;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = GroupTestConf.class)
class GroupsApplicationTests {
    @Autowired
    ApplicationContext context;

    @Test
    @Before
    void contextLoads() {
    }

}
