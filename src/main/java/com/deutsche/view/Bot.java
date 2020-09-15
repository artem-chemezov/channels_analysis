package com.deutsche.view;

import com.deutsche.view.handlers.Handler;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import javax.annotation.PostConstruct;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Service
public class Bot extends TelegramLongPollingBot {
    final int RECONNECT_PAUSE =10000;

    @Value("${bot-username}")
    private String userName;
    @Value("${bot-token}")
    private String token;

    @Autowired
    private Handler handler;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Receive new Update. updateID: " + update.getUpdateId() + "text: " + update.getMessage().getText());
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        SendMessage message = handler.handle(update);
        message.setChatId(chatId);
        execute(message);
    }

    @PostConstruct
    public void botConnect() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
            System.out.println("TelegramAPI started. Look for messages");
        } catch (TelegramApiRequestException e) {
            System.out.println("Cant Connect. Pause " + RECONNECT_PAUSE / 1000 + "sec and try again. Error: " + e.getMessage());
            try {
                Thread.sleep(RECONNECT_PAUSE);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                return;
            }
            botConnect();
        }
    }
}
