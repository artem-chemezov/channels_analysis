package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.infoobjects.ClassificationGroup;
import com.deutsche.groups.repositories.MongoGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MongoMatcherServiceImpl implements MongoMatcherService {

    @Autowired
    MongoGroupRepository mongoGroupRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Integer> findSumOfWords(String word, int groupId, int amountOfPosts) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner_id").is(String.valueOf(-groupId)));
        List<VkDataDao> users = mongoTemplate.find(query, VkDataDao.class);
        int sumOfAllWords = users.stream()
                .limit(amountOfPosts)
                .map(n -> n.getText().split("\\s+").length)
                .reduce(Integer::sum)
                .orElse(0);
        int sumOfClueWord = users.stream()
                .limit(amountOfPosts)
                .map(n -> findWord(n.getText(),word))
                .reduce(Integer::sum)
                .orElse(0);
        //mongoTemplate.find(new Query().addCriteria(Criteria.where("text").regex(".*стареть.*")), VkDataDao.class)
        return List.of(sumOfClueWord, sumOfAllWords);
    }

    @Override
    public Map<String,Double> findClassification(int groupId, int amountPosts) {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner_id").is(String.valueOf(-groupId)));
        List<VkDataDao> users = mongoTemplate.find(query, VkDataDao.class).stream().limit(amountPosts).collect(Collectors.toList());
        Map<String,Double> result = new HashMap<>();
        ClassificationGroup[] categories = ClassificationGroup.values();
        for (ClassificationGroup category:categories) {

            int sumOfAllWords = users.stream()
                    .map(n -> n.getText().split("\\s+").length)
                    .reduce(Integer::sum)
                    .orElse(0);
            int sumOfAllClueWords = 0;
            for (String word:category.getMatchers()) {
                sumOfAllClueWords += users.stream()
                        .map(n -> findWord(n.getText(),word))
                        .reduce(Integer::sum)
                        .orElse(0);
            }
            result.put(category.getCategory(), (double) (sumOfAllClueWords/sumOfAllWords));
        }
        return result;
    }

    private Integer findWord(String textString, String word) {
        List<Integer> indexes = new ArrayList<>();
        String lowerCaseTextString = textString.toLowerCase();
        String lowerCaseWord = word.toLowerCase();

        int index = 0;
        while(index != -1){
            index = lowerCaseTextString.indexOf(lowerCaseWord, index);
            if (index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes.size();
    }
}
