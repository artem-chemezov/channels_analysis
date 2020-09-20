package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.repositories.MongoGroupRepository;
import com.deutsche.groups.responses.ClassificationResponse;
import com.deutsche.groups.responses.WordMatchingResponse;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SerializationUtils;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import static com.deutsche.groups.infoobjects.Functionality.CLASSIFICATION;
import static com.deutsche.groups.infoobjects.Functionality.REPETITIONS;

@Service
public class VkDataServiceImpl implements VkDataService {
    private final ServiceActor actor;
    private final VkApiClient vk;
    private final AmqpTemplate template;
    private final MongoGroupRepository mongoGroupRepository;
    private final MongoMatcherService mongoMatcherService;
    private final MongoTemplate mongoTemplate;

    public VkDataServiceImpl(
            ServiceActor actor,
            VkApiClient vk,
            AmqpTemplate template,
            MongoGroupRepository mongoGroupRepository,
            MongoMatcherService mongoMatcherService,
            MongoTemplate mongoTemplate
    ) {
        this.actor = actor;
        this.vk = vk;
        this.template = template;
        this.mongoGroupRepository = mongoGroupRepository;
        this.mongoMatcherService = mongoMatcherService;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<VkDataDao> getPosts(String groupId, int amount) throws ClientException, ApiException {
        int id = getGroupId(groupId);
        List<VkDataDao> response = new ArrayList<>();

        for (int i = 0; i < amount / 100 + 1; i++) {
            GetResponse getResponse = vk.wall().get(actor)
                    .ownerId(-id)
                    .count(amount)
                    .offset(i)
                    .execute();

            getResponse.getItems().forEach(item -> response.add(new VkDataDao(item)));
        }

        return response;
    }

    @SneakyThrows
    @Override
    public List<VkDataDao> getPostsById(int groupId, int amount) {
        List<VkDataDao> response = new ArrayList<>();

        for (int i = 0; i < amount / 100 + 1; i++) {
            GetResponse getResponse = vk.wall().get(actor)
                    .ownerId(-groupId)
                    .count(amount)
                    .offset(i)
                    .execute();

            getResponse.getItems().forEach(item -> response.add(new VkDataDao(item)));
        }

        return response;
    }

    @SneakyThrows
    @Override
    @Async
    public Future<ResponseEntity> addPosts(String name, int amount) {
        List<VkDataDao> posts = getPosts(name, amount);
        posts.forEach(mongoGroupRepository::save);
        return AsyncResult.forValue(new ResponseEntity(HttpStatus.OK));
    }

    @Override
    public ResponseEntity getRepetitions(String chatId, String word, String groupId, int amountPosts) {
        if (checkConnection(groupId)) {
            template.convertAndSend("myVkTasksQueue", new ArrayList(List.of(REPETITIONS.getName(), chatId, word, groupId, amountPosts)));
            System.out.println("Sended to queue");
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity getClassification(String chatId, String groupId, int amountPosts) {
        if (checkConnection(groupId)) {
            template.convertAndSend("myVkTasksQueue", new ArrayList(List.of(CLASSIFICATION.getName(), chatId, groupId, amountPosts)));
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

    @SneakyThrows
    @Override
    public int getGroupId(String name){
        List<GroupFull> resp = vk.groups().getById(actor).groupId(name).execute();
        return resp.get(0).getId();
    }

    @Autowired
    public void messageListenerContainer(SimpleMessageListenerContainer container) {
        container.setMessageListener(message -> {
            System.out.println("received from myVkTasksQueue : " + SerializationUtils.deserialize(message.getBody()));
            List tempList = SerializationUtils.deserialize(message.getBody());
            if (tempList.get(0).equals(REPETITIONS.getName())) {
                addPosts((String) tempList.get(3), (Integer) tempList.get(4));

                List<Integer> answerWords = mongoMatcherService.findSumOfWords(
                        (String) tempList.get(2),
                        getGroupId((String) tempList.get(3)),
                        (int) tempList.get(4));

                WordMatchingResponse response = new WordMatchingResponse(
                        (String) tempList.get(1),
                        (String) tempList.get(2),
                        answerWords.get(0),
                        answerWords.get(1));

                JSONObject object = new JSONObject();
                object.put("functionality", tempList.get(0));
                object.put("chatId", response.getChatId());
                object.put("word", response.getWord());
                object.put("matches", response.getMatches());
                object.put("allWords", response.getAllWords());
                template.convertAndSend("VkTasksResponseQueue", object.toString());
            } else if (tempList.get(0).equals(CLASSIFICATION.getName())) {
                addPosts((String) tempList.get(2), (Integer) tempList.get(3));

                JSONObject classifications = mongoMatcherService.findClassification(
                        getGroupId((String) tempList.get(2)),
                        (Integer) tempList.get(3));

                ClassificationResponse response = new ClassificationResponse((String) tempList.get(1), (String) tempList.get(2), classifications);
                JSONObject object = new JSONObject();
                object.put("functionality", tempList.get(0).toString());
                object.put("chatId", response.getChatId());
                object.put("groupId", response.getGroupId());
                object.put("classifications", classifications);
                template.convertAndSend("VkTasksResponseQueue", object.toString());
            }
        });
    }

}
