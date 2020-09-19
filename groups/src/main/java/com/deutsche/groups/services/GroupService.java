package com.deutsche.groups.services;


import com.deutsche.groups.dao.VkDataDao;

import java.util.List;

public interface GroupService {
    public List<VkDataDao> getPostsById(int groupId, int amount);
}
