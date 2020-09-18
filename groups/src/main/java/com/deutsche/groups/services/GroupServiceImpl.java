package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkData;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private ServiceActor actor;

    @Autowired
    private VkApiClient vk;

    @SneakyThrows
    @Override
    public List<VkData> getPosts(String name, int amount) {
        int id = getGroupId(name);

        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(-id)
                .count(amount)
                .execute();

        List<VkData> response = new ArrayList<>();
        getResponse.getItems().forEach(item -> response.add(new VkData(item)));

        return response;
    }

    private int getGroupId(String name) throws ClientException, ApiException {
        List<GroupFull> resp = vk.groups().getById(actor).groupId(name).execute();
        return resp.get(0).getId();
    }
}
