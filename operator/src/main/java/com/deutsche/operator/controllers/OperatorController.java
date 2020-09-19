package com.deutsche.operator.controllers;

import com.deutsche.operator.enums.Status;
import com.deutsche.operator.services.TelegramCommandsService;
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
    public Status getRepetitions(@RequestParam int userId, @RequestParam long chatId, @RequestParam String word, @RequestParam String group, @RequestParam int amount){
        System.out.println("recieved from Bot");
        return commandsService.getWordRepetitions(userId, chatId, word, group, amount);
    }

    @ResponseBody
    @GetMapping("/posts")
    public Object[] getPosts(@RequestParam int userId, @RequestParam long chatId, @RequestParam String group, @RequestParam int amount){
        return commandsService.getPosts(userId, group, amount);
    }

    @ResponseBody
    @GetMapping("/classification")
    public Status getClassification(@RequestParam int userId, @RequestParam long chatId, @RequestParam String group, @RequestParam int amount){
        System.out.println("recieved from Bot");
        return commandsService.getClassification(userId, chatId, group, amount);
    }


}
