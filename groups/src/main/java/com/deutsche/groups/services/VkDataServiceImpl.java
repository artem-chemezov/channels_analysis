package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.repositories.MongoGroupRepository;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class VkDataServiceImpl implements VkDataService {
    private final ServiceActor actor;
    private final VkApiClient vk;
    private final AmqpTemplate template;
    private final MongoGroupRepository mongoGroupRepository;

    public VkDataServiceImpl(ServiceActor actor, VkApiClient vk, AmqpTemplate template, MongoGroupRepository mongoGroupRepository) {
        this.actor = actor;
        this.vk = vk;
        this.template = template;
        this.mongoGroupRepository = mongoGroupRepository;
    }

    @Override
    public List<VkDataDao> getPosts(String groupId, int amount)  throws ClientException, ApiException {
        int id = getGroupId(groupId);

        GetResponse getResponse = vk.wall().get(actor)
                .ownerId(-id)
                .count(amount)
                .execute();

        List<VkDataDao> response = new ArrayList<>();
        getResponse.getItems().forEach(item -> response.add(new VkDataDao(item)));

        return response;
    }

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

    @SneakyThrows
    @Override
    @Async
    public Future<ResponseEntity> addPosts(String name, int amount) {
        List<VkDataDao> posts = getPosts(name, amount);
        posts.forEach(post -> mongoGroupRepository.save(post));
        return AsyncResult.forValue(new ResponseEntity(HttpStatus.OK));
    }

    @Override
    public ResponseEntity getRepetitions(String word, String groupId, int amountPosts) {
        if (checkConnection(groupId)) {
            template.convertAndSend("myVkTasksQueue", List.of(word, groupId, amountPosts));
            System.out.println("Sended to queue");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public boolean checkConnection(String groupId) {
        try {
            return getPosts(groupId, 1).get(0).getOwner_id() != null;
        } catch (ClientException | ApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getGroupId(String name) throws ClientException, ApiException {
        List<GroupFull> resp = vk.groups().getById(actor).groupId(name).execute();
        return resp.get(0).getId();
    }
}
