package com.deutsche.operator.services;

import com.deutsche.operator.enums.Status;
import com.deutsche.operator.repo.GroupsRepo;
import com.deutsche.operator.repo.SavedPosts;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelegramCommandsService implements CommandsService {
    @Value("${groups.url}")
    protected String groupsUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SavedPosts savedPosts;

    @Autowired
    private CheckConditionsService checkConditionsService;
    @Autowired
    private GroupsRepo groupRepo;
    @Autowired
    private RestService restService;

    @SneakyThrows
    @Override
    public Status getWordRepetitions(int userId, long chatId, String word, String group, int amount) {
        Status checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            return Status.UNKNOWNUSER;
        }

        //int groupId = restService.getIdGroupByName(group);
        return restService.repetitions(chatId, word, group, amount);
    }

    @SneakyThrows
    @Override
    public Status getClassification(int userId, long chatId, String group, int amount) {
        Status checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            return Status.UNKNOWNUSER;
        }

        int groupId = restService.getIdGroupByName(group);
        return restService.classification(chatId, groupId, amount);
    }

    @SneakyThrows
    @Override
    public Object[] getPosts(int userId, String group, int amount) {
        Status checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            String[] error = {"error",  checkUserVar.code + ""};
            return error;
        }
        String query = groupsUrl + "group/getFreshPostId?";
        query += "name=" + group;
        System.out.println(query);
        String postFreshId = restTemplate.getForObject(query, String.class);
        if (savedPosts.findByPostId(postFreshId) == null){
            int groupId = restService.getIdGroupByName(group);
            return restService.posts(groupId, amount);
        }
        else {
            int groupId = restService.getIdGroupByName(group);
            return restService.posts(groupId, amount);
        }

    }

    @Override
    public Status getStatistics(int userId, long chatId, String group, int amount) {
        Status checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            return Status.UNKNOWNUSER;
        }

        int groupId = restService.getIdGroupByName(group);
        return restService.classification(chatId, groupId, amount);
    }
}
