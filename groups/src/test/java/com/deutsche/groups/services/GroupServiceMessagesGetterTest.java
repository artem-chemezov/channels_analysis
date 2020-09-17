package com.deutsche.groups.services;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GroupTestConf.class)
public class GroupServiceMessagesGetterTest {
    @Autowired
    private GroupServicePostsGetter getter;

    @Test
    void testCorrectAmountReturnedMessages(){
        List<JSONObject> res = getter.getPosts("tproger", 3);
        Assertions.assertEquals(3, res.size());
    }

}