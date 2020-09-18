package com.deutsche.groups.controllers;

import com.deutsche.groups.dao.VkData;
import com.deutsche.groups.repositories.MongoGroupRepository;
import com.deutsche.groups.services.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongo")
public class MongoGroupController {

    @Autowired
    MongoGroupRepository mongoGroupRepository;

    @Autowired
    private GroupServiceImpl groupServiceImpl;

    @GetMapping("/posts")
    public @ResponseBody List<VkData> getPosts(){
        List<VkData> posts = mongoGroupRepository.findAll();
        return posts; // на 4 сервис
    }

    @RequestMapping(value = "/add", method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public HttpStatus addPosts(String name, int amount) {
        List<VkData> posts = groupServiceImpl.getPostsByName(name, amount);
        posts.forEach(post -> mongoGroupRepository.save(post));
        return HttpStatus.OK;
    }

}
