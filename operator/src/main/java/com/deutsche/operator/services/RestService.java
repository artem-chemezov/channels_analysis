package com.deutsche.operator.services;

import com.deutsche.operator.enums.GroupsDefinition;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    protected final String baseUrl = "http://localhost:8090/";

    @Autowired
    private RestTemplate restTemplate;

    public int getIdGroupByName(String name){
        String query = baseUrl + "group/getId?";
        query = query + "name=" + name;
        return restTemplate.getForObject(query, int.class).intValue();
    }
    
    public JSONObject repetitions(String word, int groupId, int amount){
        JSONObject result = new JSONObject();
        String query = baseUrl + "mongo/repetitions?";
        query += "word=" + word;
        query += "&groupId=" + groupId;
        query += "&amount=" + amount;
        return restTemplate.getForObject(query, JSONObject.class);
    }

    @SneakyThrows
    public JSONObject posts(int groupId, int amount){
        JSONObject result = new JSONObject();
        String query = baseUrl + "group/posts?";
        query += "groupId=" + groupId;
        query += "&amount=" + amount;
        System.out.println("postsURL: " + query);
        JSONArray jsonArrayStr = new JSONArray(restTemplate.getForObject(query, String.class));
        return jsonArrayStr.getJSONObject(0);
    }

    public int clasify(String word, int groupId, GroupsDefinition groupDefinition){
        JSONObject result = new JSONObject();
        if (groupDefinition == GroupsDefinition.UNSAVED){
            String query = baseUrl + "mongo/repetitions/";
            query += "word=" + word;
            query += "&groupId=" + groupId;
            return restTemplate.getForObject(query, int.class);
        }
        if(groupDefinition == GroupsDefinition.SAVED){
            return -30;
        }
        return -30;
    }
}
