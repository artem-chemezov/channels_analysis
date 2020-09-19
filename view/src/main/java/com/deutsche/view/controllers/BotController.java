package com.deutsche.view.controllers;

import com.deutsche.view.Bot;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.api.methods.send.SendMessage;

@RestController
public class BotController {
    @Autowired
    private Bot bot;

    @GetMapping("/queue")
    public String queueRepetitions(Message message){
        SendMessage sendMessage = new SendMessage();
        System.out.println(message.getBody());
//       todo
//        sendMessage.setChatId(chatId);
//        message.getBody()
//        bot.execute(sendMessage);
        return "Send message successfully";
    }
}
