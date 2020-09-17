package com.deutsche.operator.services;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TelegramCommandsHandler implements CommandsHandler {
    @Override
    public int getWordRepetitions(String word, String group, int amount) {
        return 10;
    }

    @Override
    public String getGroupClass(String group) {
        return null;
    }

    @Override
    public List<String> getStatistics(String group) {
        return null;
    }
}
