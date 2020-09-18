package com.deutsche.operator.services;

import org.json.JSONObject;

import java.util.List;

public interface CommandsService {
    JSONObject getWordRepetitions(int userId, String word, String group, int amount);
    JSONObject getGroupClass(String group);
    JSONObject getStatistics(String group);
    JSONObject getPosts(int userId, String group, int amount);
}
