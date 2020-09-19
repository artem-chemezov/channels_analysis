package com.deutsche.operator.services;

import org.json.JSONObject;

public interface CommandsService {
    Object getWordRepetitions(int userId, String word, String group, int amount);
    JSONObject getGroupClass(String group);
    JSONObject getStatistics(String group);
    Object[] getPosts(int userId, String group, int amount);
}
