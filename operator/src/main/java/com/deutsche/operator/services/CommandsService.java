package com.deutsche.operator.services;

import org.json.JSONObject;

public interface CommandsService {
    Object getWordRepetitions(int userId, long chatId, String word, String group, int amount);
    Object getClassification(int userId, long chatId, String group, int amount);
    Object getStatistics(int userId, long chatId, String group, int amount);
    Object[] getPosts(int userId, String group, int amount);
}
