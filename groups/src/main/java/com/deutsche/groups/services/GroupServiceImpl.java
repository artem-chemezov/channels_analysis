package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
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
    public List<VkDataDao> getPostsById(int groupId, int amount) {
        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(-groupId)
                .count(amount)
                .execute();

        List<VkDataDao> response = new ArrayList<>();
        getResponse.getItems().forEach(item -> response.add(new VkDataDao(item)));

        return response;
    }

    public int getGroupId(String name) throws ClientException, ApiException {
        List<GroupFull> resp = vk.groups().getById(actor).groupId(name).execute();
        return resp.get(0).getId();
    }
}
