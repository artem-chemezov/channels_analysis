package com.deutsche.view.controllers;

import com.deutsche.view.Bot;
import com.deutsche.view.commands.Functionality;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class BotController {
    @Autowired
    private Bot bot;

    @SneakyThrows
    @PostMapping("/queue")
    public String queueRepetitions(@RequestBody String message){
        SendMessage sendMessage = new SendMessage();
        JSONObject jsonMessage = new JSONObject(message);
        sendMessage.setChatId(Long.parseLong(jsonMessage.get("chatId").toString())); //244520472
        System.out.println(message);
        Optional<Functionality> functionalityMessage = Arrays.stream(Functionality.values()).filter(value -> value.name == jsonMessage.get("functionality")).findFirst();
        sendMessage.setText(functionalityMessage.get().processMessage.apply(jsonMessage));
//       todo
//        sendMessage.setChatId(chatId);
//        message.getBody()
        bot.execute(sendMessage);
        return "Send message successfully";
    }
}
