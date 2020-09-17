package com.deutsche.groups.controllers;

import com.deutsche.groups.services.GroupServicePostsGetter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServicePostsGetter getterService;

    @GetMapping("/posts")
    public List<JSONObject> getPosts(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<JSONObject> posts = getterService.getPosts(name, amount);
        //save(posts)
        return posts; // на 4 сервис
    }

    @GetMapping("/postsText")
    public List<String> getPostsText(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<JSONObject> posts = getterService.getPosts(name, amount);
        //save(posts)
        List<String> texts = new ArrayList<>();
        posts.forEach(post -> texts.add(post.get("text").toString()));
        return texts;
    }



}
