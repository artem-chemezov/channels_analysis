package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.repositories.MongoGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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



//        mongoTemplate.find(new Query().addCriteria(Criteria.where("text").regex(".*стареть.*")), VkDataDao.class)
        return List.of(sumOfAllWords, sumOfClueWord);
    }

    private Integer findWord(String textString, String word) {
        List<Integer> indexes = new ArrayList<Integer>();
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
