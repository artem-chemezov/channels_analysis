package com.deutsche.groups.controllers;

import com.deutsche.groups.dao.VkData;
import com.deutsche.groups.services.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @GetMapping("/posts")
    public @ResponseBody List<VkData> getPosts(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkData> posts = groupServiceImpl.getPosts(name, amount);
        //save(posts)
        return posts; // на 4 сервис
    }

    @GetMapping("/postsText")
    public List<String> getPostsText(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkData> posts = groupServiceImpl.getPosts(name, amount);
        //save(posts)
        List<String> texts = new ArrayList<>();
//        posts.forEach(post -> texts.add(post.get("text").toString()));
        return texts;
    }



}
