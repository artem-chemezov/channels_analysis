package com.deutsche.groups.controllers;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.services.GroupServiceImpl;
import com.deutsche.groups.services.VkDataService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupServiceImpl;
    @Autowired
    private VkDataService vkDataService;


    @SneakyThrows
    @GetMapping("/posts")
    public @ResponseBody List<VkDataDao> getPosts(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkDataDao> posts = vkDataService.getPosts(name, amount);
        //save(posts)
        return posts; // на 4 сервис
    }

    @GetMapping("/postsById")
    public @ResponseBody List<VkDataDao> getPostsById(@RequestParam("groupId") int groupId, @RequestParam("amount") int amount){
        List<VkDataDao> posts = vkDataService.getPostsById(groupId, amount);
        return posts; // на 4 сервис
    }

    @SneakyThrows
    @GetMapping("/postsText")
    public List<String> getPostsText(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkDataDao> posts = vkDataService.getPosts(name, amount);
        //save(posts)
        List<String> texts = new ArrayList<>();
//        posts.forEach(post -> texts.add(post.get("text").toString()));
        return texts;
    }

    @GetMapping("/getId")
    public int getIdGroup(@RequestParam("name") String name){
        try {
            return vkDataService.getGroupId(name);
        } catch (ClientException | ApiException e) {
            return -3;
        }
    }



}
