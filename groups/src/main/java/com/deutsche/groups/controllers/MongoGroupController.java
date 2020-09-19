package com.deutsche.groups.controllers;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.repositories.MongoGroupRepository;
import com.deutsche.groups.services.VkDataService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoGroupController {

    @Autowired
    MongoGroupRepository mongoGroupRepository;

    @Autowired
    private VkDataService vkDataService;

    @GetMapping("/posts")
    @ResponseBody
    public List<VkDataDao> getPosts(){
        List<VkDataDao> posts = mongoGroupRepository.findAll();
        return posts; // на 4 сервис
    }

    @SneakyThrows
    @RequestMapping(value = "/add", method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity addPosts(String name, int amount) {
        return vkDataService.addPosts(name, amount).get();
    }

    @GetMapping("/repetitions")
    public ResponseEntity getRepetitions(String chatId, String word, String groupId, int amountPosts) {
        return vkDataService.getRepetitions(chatId, word, groupId, amountPosts);
    }

    @GetMapping("/classification")
    public ResponseEntity getClassification(String chatId, String groupId, int amountPosts) {
        return vkDataService.getClassification(chatId, groupId, amountPosts);
    }
}
