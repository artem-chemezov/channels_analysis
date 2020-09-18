package com.deutsche.groups.repositories;

import com.deutsche.groups.dao.VkData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoGroupRepository extends MongoRepository<VkData,String> {

}
