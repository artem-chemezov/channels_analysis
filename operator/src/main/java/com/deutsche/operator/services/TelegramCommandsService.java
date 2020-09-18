package com.deutsche.operator.services;

import com.deutsche.operator.enums.GroupsDefinition;
import com.deutsche.operator.enums.UserErrors;
import com.deutsche.operator.repo.GroupsRepo;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TelegramCommandsService implements CommandsService {
    @Autowired
    private CheckConditionsService checkConditionsService;
    @Autowired
    private GroupsRepo groupRepo;
    @Autowired
    private RestService restService;

    @SneakyThrows
    @Override
    public JSONObject getWordRepetitions(int userId, String word, String group, int amount) {
        UserErrors checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            JSONObject error = new JSONObject();
            error.put("error", checkUserVar.code);
            return error;
        }

        int groupId = restService.getIdGroupByName(group);
        return restService.repetitions(word, groupId, amount);
    }

    @SneakyThrows
    @Override
    public JSONObject getPosts(int userId, String group, int amount) {
        UserErrors checkUserVar = checkConditionsService.checkUser(userId);
        if (checkUserVar.code < 0){
            JSONObject error = new JSONObject();
            error.put("error", checkUserVar.code);
            return error;
        }

        int groupId = restService.getIdGroupByName(group);
        return restService.posts(groupId, amount);
    }

    @Override
    public JSONObject getGroupClass(String group) {
        return null;
//        UserErrors checkUserVar = checkConditionsService.checkUser(userId);
//        if (checkUserVar.code < 0){
//            return mew JSONobject;//checkUserVar.code;
//        }
//
//        int groupId = restService.getIdGroupByName(group);
//        GroupsDefinition groupsDefinition = checkConditionsService.defineGroupDatabase(groupId);
//        if (groupsDefinition.code < 0){
//            return groupsDefinition.code;
//        }
//
//        return 10;
    }

    @Override
    public JSONObject getStatistics(String group) {
        return null;
    }
}
