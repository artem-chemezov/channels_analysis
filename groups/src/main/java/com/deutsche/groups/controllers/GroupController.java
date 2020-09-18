package com.deutsche.groups.controllers;

import com.deutsche.groups.dao.VkData;
import com.deutsche.groups.services.GroupServiceImpl;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @GetMapping("/postsByName")
    public @ResponseBody List<VkData> getPostsByName(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkData> posts = groupServiceImpl.getPostsByName(name, amount);
        return posts; // на 4 сервис
    }


    @GetMapping("/posts")
    public @ResponseBody List<VkData> getPosts(@RequestParam("groupId") int groupId, @RequestParam("amount") int amount){
        List<VkData> posts = groupServiceImpl.getPosts(groupId, amount);
        return posts; // на 4 сервис
    }


    @GetMapping("/postsText")
    public List<String> getPostsText(@RequestParam("name") String name, @RequestParam("amount") int amount){
        List<VkData> posts = groupServiceImpl.getPostsByName(name, amount);
        List<String> texts = new ArrayList<>();
        return texts;
    }

    @GetMapping("/getId")
    public int getIdGroup(@RequestParam("name") String name){
        try {
            return groupServiceImpl.getGroupId(name);
        } catch (ClientException | ApiException e) {
            return -3;
        }
    }

}
