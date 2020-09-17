package com.deutsche.operator.services;

import java.util.List;

public interface CommandsHandler {
    int getWordRepetitions(String word, String group, int amount);
    String getGroupClass(String group);
    List<String> getStatistics(String group);
}
