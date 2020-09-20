package com.deutsche.groups.services;

import java.util.List;

public interface MongoMatcherService {
    public List<Integer> findSumOfWords(String word, int groupId, int amountOfPosts);
}
