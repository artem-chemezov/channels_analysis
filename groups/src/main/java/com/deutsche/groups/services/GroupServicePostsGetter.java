package com.deutsche.groups.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.WallpostFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServicePostsGetter implements GroupService {
    @Autowired
    private ServiceActor actor;

    @Autowired
    private VkApiClient vk;

    @SneakyThrows
    @Override
    public List<JSONObject> getPosts(String name, int amount) {
        int id = getGroupId(name);

        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(-id)
                .count(amount)
                .execute();

        List<JSONObject> response = new ArrayList<>();
        getResponse.getItems().forEach(item -> response.add(new JSONObject(item.toString())));

        return response;
    }

    private int getGroupId(String name) throws ClientException, ApiException {
        List<GroupFull> resp = vk.groups().getById(actor).groupId(name).execute();
        return resp.get(0).getId();
    }
}
