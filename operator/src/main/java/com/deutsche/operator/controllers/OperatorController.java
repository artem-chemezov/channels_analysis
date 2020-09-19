package com.deutsche.operator.controllers;

import com.deutsche.operator.services.TelegramCommandsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {
    @Autowired
    private TelegramCommandsService commandsService;

    @ResponseBody
    @GetMapping("/repetitions")
    public Object getRepetitions(@RequestParam int userId, @RequestParam String word, @RequestParam String group, @RequestParam int amount){
        return commandsService.getWordRepetitions(userId, word, group, amount);
    }

    @ResponseBody
    @GetMapping("/posts")
    public Object[] getPosts(@RequestParam int userId, @RequestParam String group, @RequestParam int amount){
        return commandsService.getPosts(userId, group, amount);
    }

}
