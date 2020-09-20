package com.deutsche.operator.services;

import com.deutsche.operator.enums.Status;
import com.deutsche.operator.rabbit.RabbitConfiguration;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Value("${groups.url}")
    protected String groupsUrl;

    Logger logger = Logger.getLogger(RabbitConfiguration.class);
    @Autowired
    private SimpleMessageListenerContainer simpleMessageListenerContainer;

    @Autowired
    private RestTemplate restTemplate;

    public int getIdGroupByName(String name){
        String query = groupsUrl + "group/getId?";
        query = query + "name=" + name;
        return restTemplate.getForObject(query, int.class).intValue();
    }
    
    public Status repetitions(long chatId, String word, String groupName, int amount){
        JSONObject result = new JSONObject();
        String query = groupsUrl + "mongo/repetitions?";
        query += "chatId=" + chatId;
        query += "&word=" + word;
        query += "&groupId=" + groupName;
        query += "&amountPosts=" + amount;
        System.out.println("url operator-mongo repetitions: " + query);
        ResponseEntity answer = restTemplate.getForEntity(query, Object.class);
        if (answer.getStatusCode() == HttpStatus.OK) {
            return Status.ADDEDTOQUEUE;
        }
        return Status.CONNECTIONERROR;
    }


    public Status classification(long chatId, int groupId, int amount){
        JSONObject result = new JSONObject();
        String query = groupsUrl + "mongo/classification?";
        query += "chatId=" + chatId;
        query += "&groupId=" + groupId;
        query += "&amountPosts=" + amount;
        System.out.println("url operator-mongo repetitions: " + query);
        ResponseEntity answer = restTemplate.getForEntity(query, Object.class);
        if (answer.getStatusCode() == HttpStatus.OK) {
            return Status.ADDEDTOQUEUE;
        }
        return Status.CONNECTIONERROR;
    }

    @SneakyThrows
    public Object[] posts(int groupId, int amount){
        JSONObject result = new JSONObject();
        String query = groupsUrl + "group/postsById?";
        query += "groupId=" + groupId;
        query += "&amount=" + amount;
        System.out.println("postsURL: " + query);
        ResponseEntity<Object[]> answer = restTemplate.getForEntity(query, Object[].class);
        return answer.getBody();
    }

    public int clasify(String word, int groupId){
        JSONObject result = new JSONObject();
        String query = groupsUrl + "mongo/repetitions/";
        query += "word=" + word;
        query += "&groupId=" + groupId;
        return restTemplate.getForObject(query, int.class);
    }
}
