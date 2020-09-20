package com.deutsche.groups.services;

import com.deutsche.groups.dao.VkDataDao;
import com.deutsche.groups.repositories.MongoGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoMatcherServiceImpl implements MongoMatcherService {

    @Autowired
    MongoGroupRepository mongoGroupRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void findSumOfWords() {
        Query query = new Query();
        query.addCriteria(Criteria.where("owner_id").is(1600240800));
        List<VkDataDao> users = mongoTemplate.find(query, VkDataDao.class);
    //    mongoTemplate.mapReduce(ExecutableMapReduceOperation.MapReduceWithMapFunction(VkDataDao.class))
        System.out.println("adsa");


//        mongoTemplate.find(new Query().addCriteria(Criteria.where("text").regex(".*стареть.*")), VkDataDao.class)

    }
}
