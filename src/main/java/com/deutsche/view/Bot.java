package com.deutsche.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@AllArgsConstructor
@NoArgsConstructor
public class Bot extends TelegramLongPollingBot {

    final int RECONNECT_PAUSE =10000;

    @Setter
    @Getter
    String userName;// = "Test_java_09_09_2020_bot";
    @Setter
    @Getter
    String token;// = "1335623903:AAHXfpsJqnRkwFSOiuD_rqUWpVgLJ7duMbg";

    @Override
    public String getBotUsername() {
        return userName;
    }


    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Receive new Update. updateID: " + update.getUpdateId() + "text: " + update.getMessage().getText());

        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();

        if (inputText.startsWith("/start")) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Hello. This is start message");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return token;
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
        Bot bot = new Bot("Test_java_09_09_2020_bot", "1335623903:AAHXfpsJqnRkwFSOiuD_rqUWpVgLJ7duMbg");
        bot.botConnect();
    }
}
