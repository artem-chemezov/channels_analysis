package com.deutsche.view.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;

import java.util.List;

public interface Handler {
    SendMessage handle(Update update);
}
