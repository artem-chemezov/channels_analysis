package com.deutsche.view;

import com.deutsche.App;
import com.deutsche.view.tools.Keyboard;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@SpringBootApplication
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class Bot extends TelegramLongPollingBot {
    final int RECONNECT_PAUSE =10000;
    @Setter
    @Getter
    String userName;
    @Setter
    @Getter
    String token;
    @Setter
    @Singleton
    private Handler handler;

    @Autowired
    public Bot(Handler handler) {
        this.handler = handler;
    }

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
        SendMessage message = handler.handle(update, List.of("qwe", "asd"));
        message.setChatId(chatId);
        execute(message);

//        if (inputText.startsWith("/start")) {
//            SendMessage message = new SendMessage()
//                                .setChatId(chatId)
//                                .setText("Hello. This is start message");
//            execute(message);
//        }
//        if (inputText.startsWith("/stop")) {
//            SendMessage message = new SendMessage()
//                    .setChatId(chatId)
//                    .setText("Options");
//            message = handler.handle(message, List.of("qwe", "asd"));
//            execute(message);
//        }
    }

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

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Bot.class, args);
        Bot bot = Bot.builder()
                .userName("Test_java_09_09_2020_bot")
                .token("1335623903:AAHXfpsJqnRkwFSOiuD_rqUWpVgLJ7duMbg")
                .handler(new ResponseHandler())
                .build();
        bot.botConnect();
    }
}
