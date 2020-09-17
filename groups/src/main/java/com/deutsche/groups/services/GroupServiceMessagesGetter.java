package com.deutsche.groups.services;

import com.vk.api.sdk.client.VkApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GroupServiceMessagesGetter implements GroupService {
    @Autowired
    private VkApiClient vk;

    @Override
    public List<String> getMessages() {

        return null;
    }

    private int getGroupId(String name){

        return 3;
    }
}
