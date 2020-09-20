package com.deutsche.groups.services;

import java.util.List;
import java.util.Map;

public interface MongoMatcherService {
    public List<Integer> findSumOfWords(String word, int groupId, int amountOfPosts);
    public Map<String,Double> findClassification(int groupId, int amountPosts);
}
