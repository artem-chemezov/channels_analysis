package com.deutsche.view.tools;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public interface Keyboard {
    public void setButtons(SendMessage sendMessage);
    public void setEmpty(SendMessage sendMessage);
}
