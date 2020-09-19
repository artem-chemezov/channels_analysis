package com.deutsche.groups.services;


import com.deutsche.groups.dao.VkDataDao;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.Future;

public interface VkDataService {
    public List<VkDataDao> getPosts(String groupId, int amount) throws ClientException, ApiException;
    public List<VkDataDao> getPostsById(int groupId, int amount);
    public Future<ResponseEntity> addPosts(String name, int amount);
    public ResponseEntity getRepetitions(String word, String groupId, int amountPosts);
    public boolean checkConnection(String groupId);
    public int getGroupId(String name) throws ClientException, ApiException ;
}
