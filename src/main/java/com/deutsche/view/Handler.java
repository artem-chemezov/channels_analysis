package com.deutsche.view;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.List;
@Component
public interface Handler {
    SendMessage handle(Update update, List<String> params);
}
