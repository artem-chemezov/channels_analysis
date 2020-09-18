package com.deutsche.groups.services;


import com.deutsche.groups.dao.VkData;

import java.util.List;

public interface GroupService {
    public List<VkData> getPostsByName(String name, int amount);
    public List<VkData> getPosts(int groupId, int amount);
}
