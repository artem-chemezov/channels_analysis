package com.deutsche.groups.services;

import org.json.JSONObject;

import java.util.List;

public interface MongoMatcherService {
    public List<Integer> findSumOfWords(String word, int groupId, int amountOfPosts);
    public JSONObject findClassification(int groupId, int amountPosts);
}
