package com.deutsche.groups.repositories;

import com.deutsche.groups.dao.VkDataDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGroupRepository extends MongoRepository<VkDataDao,String> {

}
