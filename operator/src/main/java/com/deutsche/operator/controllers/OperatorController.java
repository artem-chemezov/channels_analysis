package com.deutsche.operator.controllers;

import com.deutsche.operator.services.TelegramCommandsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatorController {
    @Autowired
    private TelegramCommandsHandler commandsHandler;

    @GetMapping("/repetitions")
    public int getRepetitions(@RequestParam String word, @RequestParam String group, @RequestParam int amount){
        return commandsHandler.getWordRepetitions(word, group, amount);
    }
}
