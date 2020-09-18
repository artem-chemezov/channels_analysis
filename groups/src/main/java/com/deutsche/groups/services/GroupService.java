package com.deutsche.groups.services;


import com.deutsche.groups.dao.VkData;

import java.util.List;

public interface GroupService {
    public List<VkData> getPosts(String name, int amount);
}
